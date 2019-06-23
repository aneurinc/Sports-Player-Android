package com.nsc9012.sportsplayer.features.athletes

import android.os.Bundle
import android.view.View
import com.nsc9012.sportsplayer.R
import com.nsc9012.sportsplayer.core.exception.Failure
import com.nsc9012.sportsplayer.core.extension.*
import com.nsc9012.sportsplayer.core.navigation.Navigator
import com.nsc9012.sportsplayer.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_athlete_details.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class AthleteDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_ATHLETE = "param_athlete"

        fun forAthlete(athlete: AthleteView): AthleteDetailsFragment {
            val athleteDetailsFragment = AthleteDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_ATHLETE, athlete)
            athleteDetailsFragment.arguments = arguments

            return athleteDetailsFragment
        }
    }

    @Inject
    lateinit var athleteDetailsAnimator: AthleteDetailsAnimator

    @Inject
    lateinit var navigator: Navigator

    private lateinit var viewModel: AthleteDetailsViewModel

    override fun layoutId() = R.layout.fragment_athlete_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { athleteDetailsAnimator.postponeEnterTransition(it) }

        viewModel = viewModel(viewModelFactory) {
            observe(athleteDetails, ::renderAthleteDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            viewModel.loadAthleteDetails((arguments?.get(PARAM_ATHLETE) as AthleteView).id)
        } else {
            athleteDetailsAnimator.scaleUpView(clipPlay)
            athleteDetailsAnimator.cancelTransition(athletePicture)
            athletePicture.loadFromUrl((arguments!![PARAM_ATHLETE] as AthleteView).pictureUrl)
        }
    }

    override fun onBackPressed() {
        athleteDetailsAnimator.fadeInvisible(scrollView, athleteDetails)
        if (clipPlay.isVisible())
            athleteDetailsAnimator.scaleDownView(clipPlay)
        else
            athleteDetailsAnimator.cancelTransition(athletePicture)
    }

    private fun renderAthleteDetails(athlete: AthleteDetailsView?) {
        athlete?.let {
            with(athlete) {
                activity?.let {
                    athletePicture.loadUrlAndPostponeEnterTransition(pictureUrl, it)
                    it.toolbar.title = name
                }
                detailDob.text = dateOfBirth
                detailHometown.text = hometown
                detailBio.text = bio
                clipPlay.setOnClickListener {
                    navigator.showVideoPlayer(activity!!, videoUrl)
                }
            }
        }
        athleteDetailsAnimator.fadeVisible(scrollView, athleteDetails)
        athleteDetailsAnimator.scaleUpView(clipPlay)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is AthleteFailure.AthleteNotFound -> {
                notify(R.string.failure_athlete_non_existent); close()
            }
        }
    }
}