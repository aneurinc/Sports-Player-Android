package com.nsc9012.sportsplayer.features.athletes

import com.nsc9012.sportsplayer.core.interactor.UseCase
import javax.inject.Inject

class GetAthletes
@Inject constructor(
    private val repository: AthletesRepository
) : UseCase<List<Athlete>, UseCase.None>() {

    override suspend fun run(params: None) = repository.athletes()

}
