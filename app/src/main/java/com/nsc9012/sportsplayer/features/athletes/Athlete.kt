package com.nsc9012.sportsplayer.features.athletes

import com.nsc9012.sportsplayer.core.extension.empty

data class Athlete(
    val id: Int,
    val pictureUrl: String
) {
    companion object {
        fun empty() = Athlete(0, String.empty())
    }
}
