package com.nsc9012.sportsplayer.features.athletes

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AthletesService
@Inject constructor(retrofit: Retrofit) : AthletesApi {

    private val athletesApi by lazy { retrofit.create(AthletesApi::class.java) }

    override fun athletes() = athletesApi.athletes()
    override fun athleteDetails(athleteId: Int) = athletesApi.athleteDetails(athleteId)
}
