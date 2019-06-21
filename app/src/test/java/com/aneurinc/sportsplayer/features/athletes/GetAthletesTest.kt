package com.aneurinc.sportsplayer.features.athletes

import com.aneurinc.sportsplayer.UnitTest
import com.aneurinc.sportsplayer.core.functional.Either
import com.aneurinc.sportsplayer.core.interactor.UseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetAthletesTest : UnitTest() {

    private lateinit var getAthletes: GetAthletes

    @Mock private lateinit var athletesRepository: AthletesRepository

    @Before fun setUp() {
        getAthletes = GetAthletes(athletesRepository)
        given { athletesRepository.athletes() }.willReturn(Either.Right(listOf(Athlete.empty())))
    }

    @Test fun `should get data from repository`() {
        runBlocking { getAthletes.run(UseCase.None()) }

        verify(athletesRepository).athletes()
        verifyNoMoreInteractions(athletesRepository)
    }
}
