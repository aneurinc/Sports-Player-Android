package com.aneurinc.sportsplayer.features.athletes

import com.aneurinc.sportsplayer.core.interactor.UseCase
import javax.inject.Inject


class GetAthleteDetails
@Inject constructor(private val repository : AthletesRepository) : UseCase<AthleteDetails, GetAthleteDetails.Params>() {

    override suspend fun run(params: Params) = repository.athleteDetails(params.id)

    data class Params(val id: Int)

}
