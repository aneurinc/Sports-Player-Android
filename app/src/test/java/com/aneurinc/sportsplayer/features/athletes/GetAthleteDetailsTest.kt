
package com.aneurinc.sportsplayer.features.athletes

import com.aneurinc.sportsplayer.UnitTest
import com.aneurinc.sportsplayer.core.functional.Either
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetAthleteDetailsTest : UnitTest() {

    private val ATHLETE_ID = 1

    private lateinit var getAthleteDetails: GetAthleteDetails

    @Mock private lateinit var athletesRepository: AthletesRepository

    @Before fun setUp() {
        getAthleteDetails = GetAthleteDetails(athletesRepository)
        given { athletesRepository.athleteDetails(ATHLETE_ID) }.willReturn(Either.Right(AthleteDetails.empty()))
    }

    @Test fun `should get data from repository`() {
        runBlocking { getAthleteDetails.run(GetAthleteDetails.Params(ATHLETE_ID)) }

        verify(athletesRepository).athleteDetails(ATHLETE_ID)
        verifyNoMoreInteractions(athletesRepository)
    }
}
