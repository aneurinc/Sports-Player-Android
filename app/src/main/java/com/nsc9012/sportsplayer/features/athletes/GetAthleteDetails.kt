package com.nsc9012.sportsplayer.features.athletes

import com.nsc9012.sportsplayer.core.interactor.UseCase
import javax.inject.Inject


class GetAthleteDetails
@Inject constructor(private val repository : AthletesRepository) : UseCase<AthleteDetails, GetAthleteDetails.Params>() {

    override suspend fun run(params: Params) = repository.athleteDetails(params.id)

    data class Params(val id: Int)

}
