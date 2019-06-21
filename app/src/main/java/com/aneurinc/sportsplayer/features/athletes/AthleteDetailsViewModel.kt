package com.aneurinc.sportsplayer.features.athletes

import android.arch.lifecycle.MutableLiveData
import com.aneurinc.sportsplayer.core.platform.BaseViewModel
import javax.inject.Inject

class AthleteDetailsViewModel
@Inject constructor(
    private val getAthleteDetails: GetAthleteDetails
) : BaseViewModel() {

    var athleteDetails: MutableLiveData<AthleteDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) =
        getAthleteDetails(GetAthleteDetails.Params(movieId)) { it.either(::handleFailure, ::handleMovieDetails) }

    private fun handleMovieDetails(athlete: AthleteDetails) {
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