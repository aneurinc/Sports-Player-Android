package com.aneurinc.sportsplayer.features.athletes

import com.aneurinc.sportsplayer.core.extension.empty

data class Athlete(
    val id: Int,
    val pictureUrl: String
) {
    companion object {
        fun empty() = Athlete(0, String.empty())
    }
}
