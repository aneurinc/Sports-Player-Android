
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

class AthletesViewModelTest : AndroidTest() {

    private lateinit var athletesViewModel: AthletesViewModel

    @Mock private lateinit var getAthletes: GetAthletes

    @Before
    fun setUp() {
        athletesViewModel = AthletesViewModel(getAthletes)
    }

    @Test fun `loading athletes should update live data`() {
        val athleteList = listOf(Athlete(0, "https://url1..."), Athlete(1, "https://url2..."))
        given { runBlocking { getAthletes.run(eq(any())) } }.willReturn(Either.Right(athleteList))

        athletesViewModel.athletes.observeForever {
            it!!.size shouldEqualTo 2
            it[0].id shouldEqualTo 0
            it[0].pictureUrl shouldEqualTo "https://url1..."
            it[1].id shouldEqualTo 1
            it[1].pictureUrl shouldEqualTo "https://url2..."
        }

        runBlocking { athletesViewModel.loadAthletes() }
    }
}