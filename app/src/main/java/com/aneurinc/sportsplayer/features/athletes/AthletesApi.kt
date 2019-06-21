package com.aneurinc.sportsplayer.features.athletes

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

internal interface AthletesApi {

    companion object {
        private const val ATHLETE_ID = "athlete_id"
        private const val ATHLETES = "athletes"
    }

    @GET(ATHLETES) fun athletes(): Call<List<AthleteEntity>>

}
