package com.nsc9012.sportsplayer.features.athletes

import com.nsc9012.sportsplayer.core.extension.empty

data class AthleteDetails(
    val id: Int,
    val name: String,
    val dateOfBirth: String,
    val hometown: String,
    val bio: String,
    val pictureUrl: String,
    val videoUrl: String
) {

    companion object {
        fun empty() = AthleteDetails(
            0,
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty()
        )
    }
}
