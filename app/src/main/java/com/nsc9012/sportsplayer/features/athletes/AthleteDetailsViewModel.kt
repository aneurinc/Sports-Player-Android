package com.nsc9012.sportsplayer.features.athletes

import android.arch.lifecycle.MutableLiveData
import com.nsc9012.sportsplayer.core.platform.BaseViewModel
import javax.inject.Inject

class AthleteDetailsViewModel
@Inject constructor(
    private val getAthleteDetails: GetAthleteDetails
) : BaseViewModel() {

    var athleteDetails: MutableLiveData<AthleteDetailsView> = MutableLiveData()

    fun loadAthleteDetails(athleteId: Int) =
        getAthleteDetails(GetAthleteDetails.Params(athleteId)) { it.either(::handleFailure, ::handleAthleteDetails) }

    private fun handleAthleteDetails(athlete: AthleteDetails) {
        this.athleteDetails.value = AthleteDetailsView(
            athlete.id,
            athlete.name,
            athlete.dateOfBirth,
            athlete.hometown,
            athlete.bio,
            athlete.pictureUrl,
            athlete.videoUrl
        )
    }
}