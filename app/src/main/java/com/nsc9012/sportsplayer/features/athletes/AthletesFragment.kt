package com.nsc9012.sportsplayer.features.athletes

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.nsc9012.sportsplayer.R
import com.nsc9012.sportsplayer.core.exception.Failure
import com.nsc9012.sportsplayer.core.extension.*
import com.nsc9012.sportsplayer.core.navigation.Navigator
import com.nsc9012.sportsplayer.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_athletes.*
import javax.inject.Inject

class AthletesFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var adapter: AthletesAdapter

    private lateinit var viewModel: AthletesViewModel

    override fun layoutId() = R.layout.fragment_athletes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        viewModel = viewModel(viewModelFactory) {
            observe(athletes, ::renderAthletesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadAthletesList()
    }

    private fun initializeView() {
        athleteList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        athleteList.adapter = adapter
        adapter.clickListener = { athleteView, navigationExtras ->
            navigator.showAthleteDetails(activity!!, athleteView, navigationExtras) }
    }

    private fun renderAthletesList(athletes: List<AthleteView>?) {
        adapter.collection = athletes.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is AthleteFailure.ListNotAvailable -> renderFailure(R.string.failure_athletes_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        athleteList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadAthletesList)
    }

    private fun loadAthletesList() {
        emptyView.invisible()
        athleteList.visible()
        showProgress()
        viewModel.loadAthletes()
    }
}
