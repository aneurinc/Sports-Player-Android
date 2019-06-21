package com.aneurinc.sportsplayer.features.athletes


data class AthleteDetailsView(
    val id: Int,
    val name: String,
    val dateOfBirth: String,
    val hometown: String,
    val bio: String,
    val pictureUrl: String,
    val videoUrl: String
)