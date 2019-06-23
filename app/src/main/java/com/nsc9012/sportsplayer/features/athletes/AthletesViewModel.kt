package com.nsc9012.sportsplayer.features.athletes

import android.arch.lifecycle.MutableLiveData
import com.nsc9012.sportsplayer.core.interactor.UseCase.*
import com.nsc9012.sportsplayer.core.platform.BaseViewModel
import javax.inject.Inject

class AthletesViewModel
@Inject constructor(private val getAthletes: GetAthletes) : BaseViewModel() {

    var athletes: MutableLiveData<List<AthleteView>> = MutableLiveData()

    fun loadAthletes() = getAthletes(None()) { it.either(::handleFailure, ::handleAthletesList) }

    private fun handleAthletesList(athletes: List<Athlete>) {
        this.athletes.value = athletes.map { AthleteView(it.id, it.pictureUrl) }
    }
}