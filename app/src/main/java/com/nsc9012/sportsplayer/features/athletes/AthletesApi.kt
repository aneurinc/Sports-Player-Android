package com.nsc9012.sportsplayer.features.athletes

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

internal interface AthletesApi {

    companion object {
        private const val ATHLETE_ID = "athlete_id"
        private const val ATHLETES = "athletes"
        private const val ATHLETE_DETAILS = "athlete/{$ATHLETE_ID}"
    }

    @GET(ATHLETES) fun athletes(): Call<List<AthleteEntity>>
    @GET(ATHLETE_DETAILS) fun athleteDetails(@Path(ATHLETE_ID) athleteId: Int): Call<AthleteDetailsEntity>

}
