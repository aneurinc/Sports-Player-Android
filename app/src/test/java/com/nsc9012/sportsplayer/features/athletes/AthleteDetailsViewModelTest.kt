
package com.nsc9012.sportsplayer.features.athletes

import com.nsc9012.sportsplayer.AndroidTest
import com.nsc9012.sportsplayer.core.functional.Either
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class AthleteDetailsViewModelTest : AndroidTest() {

    private lateinit var athleteDetailsViewModel: AthleteDetailsViewModel

    @Mock private lateinit var getAthleteDetails: GetAthleteDetails

    @Before
    fun setUp() {
        athleteDetailsViewModel = AthleteDetailsViewModel(getAthleteDetails)
    }

    @Test fun `loading athlete details should update live data`() {
        val athleteDetails = AthleteDetails(
            0,
            "Lionel Messi",
            "06/23/1987",
            "Rosario, Argentina",
            "Professional football player for Barcelona...",
            "https://...",
            "https://..."
        )
        given { runBlocking { getAthleteDetails.run(eq(any())) } }.willReturn(Either.Right(athleteDetails))

        athleteDetailsViewModel.athleteDetails.observeForever {
            with(it!!) {
                id shouldEqualTo 0
                name shouldEqualTo "Lionel Messi"
                dateOfBirth shouldEqualTo "06/23/1987"
                hometown shouldEqualTo "Rosario, Argentina"
                bio shouldEqualTo "Professional football player for Barcelona..."
                pictureUrl shouldEqualTo "https://..."
                videoUrl shouldEqualTo "https://..."
            }
        }

        runBlocking { athleteDetailsViewModel.loadAthleteDetails(0) }
    }
}