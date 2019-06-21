package com.aneurinc.sportsplayer.features.athletes

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AthletesService
@Inject constructor(retrofit: Retrofit) : AthletesApi {

    private val moviesApi by lazy { retrofit.create(AthletesApi::class.java) }

    override fun athletes() = moviesApi.athletes()
}
