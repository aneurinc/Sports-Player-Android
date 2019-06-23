package com.nsc9012.sportsplayer.features.athletes

import com.nsc9012.sportsplayer.core.extension.empty
import com.google.gson.annotations.SerializedName

data class AthleteDetailsEntity(
    val id: Int,
    val name: String,

    @SerializedName("date_of_birth")
    val dateOfBirth: String,

    val hometown: String,
    val bio: String,

    @SerializedName("picture_url")
    val pictureUrl: String,

    @SerializedName("video_url")
    val videoUrl: String
) {

    companion object {
        fun empty() = AthleteDetailsEntity(
            0,
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty()
        )
    }

    fun toAthleteDetails() = AthleteDetails(id, name, dateOfBirth, hometown, bio, pictureUrl, videoUrl)
}