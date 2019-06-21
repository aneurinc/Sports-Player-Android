package com.aneurinc.sportsplayer.features.athletes

import android.arch.lifecycle.MutableLiveData
import com.aneurinc.sportsplayer.core.interactor.UseCase.*
import com.aneurinc.sportsplayer.core.platform.BaseViewModel
import javax.inject.Inject

class AthletesViewModel
@Inject constructor(private val getAthletes: GetAthletes) : BaseViewModel() {

    var athletes: MutableLiveData<List<AthleteView>> = MutableLiveData()

    fun loadAthletes() = getAthletes(None()) { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(athletes: List<Athlete>) {
        this.athletes.value = athletes.map { AthleteView(it.id, it.pictureUrl) }
    }
}