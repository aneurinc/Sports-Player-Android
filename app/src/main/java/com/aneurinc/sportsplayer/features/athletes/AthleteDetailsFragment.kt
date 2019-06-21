package com.aneurinc.sportsplayer.features.athletes

import android.os.Bundle
import android.view.View
import com.aneurinc.sportsplayer.R
import com.aneurinc.sportsplayer.core.exception.Failure
import com.aneurinc.sportsplayer.core.extension.*
import com.aneurinc.sportsplayer.core.navigation.Navigator
import com.aneurinc.sportsplayer.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_athlete_details.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class AthleteDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MOVIE = "param_movie"

        fun forAthlete(athlete: AthleteView): AthleteDetailsFragment {
            val movieDetailsFragment = AthleteDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MOVIE, athlete)
            movieDetailsFragment.arguments = arguments

            return movieDetailsFragment
        }
    }

    @Inject
    lateinit var movieDetailsAnimator: AthleteDetailsAnimator

    @Inject
    lateinit var navigator: Navigator

    private lateinit var viewModel: AthleteDetailsViewModel

    override fun layoutId() = R.layout.fragment_athlete_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }

        viewModel = viewModel(viewModelFactory) {
            observe(athleteDetails, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            viewModel.loadAthleteDetails((arguments?.get(PARAM_MOVIE) as AthleteView).id)
        } else {
            movieDetailsAnimator.scaleUpView(moviePlay)
            movieDetailsAnimator.cancelTransition(athletePicture)
            athletePicture.loadFromUrl((arguments!![PARAM_MOVIE] as AthleteView).pictureUrl)
        }
    }

    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.isVisible())
            movieDetailsAnimator.scaleDownView(moviePlay)
        else
            movieDetailsAnimator.cancelTransition(athletePicture)
    }

    private fun renderMovieDetails(athlete: AthleteDetailsView?) {
        athlete?.let {
            with(athlete) {
                activity?.let {
                    athletePicture.loadUrlAndPostponeEnterTransition(pictureUrl, it)
                    it.toolbar.title = name
                }
                detailDob.text = dateOfBirth
                detailHometown.text = hometown
                detailBio.text = bio
                moviePlay.setOnClickListener {
                    navigator.showVideoPlayer(activity!!, videoUrl)
                }
            }
        }
        movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
        movieDetailsAnimator.scaleUpView(moviePlay)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is AthleteFailure.NonExistentMovie -> {
                notify(R.string.failure_movie_non_existent); close()
            }
        }
    }
}