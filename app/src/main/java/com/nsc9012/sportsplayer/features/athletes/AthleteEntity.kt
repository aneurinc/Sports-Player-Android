package com.nsc9012.sportsplayer.features.athletes

import com.google.gson.annotations.SerializedName

data class AthleteEntity(
    val id: Int,
    @SerializedName("picture_url")
    val pictureUrl: String

) {
    fun toAthlete() = Athlete(id, pictureUrl)
}