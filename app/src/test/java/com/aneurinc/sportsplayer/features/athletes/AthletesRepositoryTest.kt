
package com.aneurinc.sportsplayer.features.athletes

import com.aneurinc.sportsplayer.UnitTest
import com.aneurinc.sportsplayer.core.exception.Failure.*
import com.aneurinc.sportsplayer.core.extension.empty
import com.aneurinc.sportsplayer.core.functional.Either
import com.aneurinc.sportsplayer.core.platform.NetworkHandler
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response

class AthletesRepositoryTest : UnitTest() {

    private lateinit var networkRepository: AthletesRepository.Network

    @Mock private lateinit var networkHandler: NetworkHandler
    @Mock private lateinit var service: AthletesService

    @Mock private lateinit var athletesCall: Call<List<AthleteEntity>>
    @Mock private lateinit var athletesResponse: Response<List<AthleteEntity>>
    @Mock private lateinit var athleteDetailsCall: Call<AthleteDetailsEntity>
    @Mock private lateinit var athleteDetailsResponse: Response<AthleteDetailsEntity>

    @Before fun setUp() {
        networkRepository = AthletesRepository.Network(networkHandler, service)
    }

    @Test fun `should return empty list by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { athletesResponse.body() }.willReturn(null)
        given { athletesResponse.isSuccessful }.willReturn(true)
        given { athletesCall.execute() }.willReturn(athletesResponse)
        given { service.athletes() }.willReturn(athletesCall)

        val athletes = networkRepository.athletes()

        athletes shouldEqual Either.Right(emptyList<Athlete>())
        verify(service).athletes()
    }

    @Test fun `should get athlete list from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { athletesResponse.body() }.willReturn(listOf(AthleteEntity(1, "https://...")))
        given { athletesResponse.isSuccessful }.willReturn(true)
        given { athletesCall.execute() }.willReturn(athletesResponse)
        given { service.athletes() }.willReturn(athletesCall)

        val athletes = networkRepository.athletes()

        athletes shouldEqual Either.Right(listOf(AthleteEntity(1, "https://...")))
        verify(service).athletes()
    }

    @Test fun `athletes service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val athletes = networkRepository.athletes()

        athletes shouldBeInstanceOf Either::class.java
        athletes.isLeft shouldEqual true
        athletes.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `athletes service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(null)

        val athletes = networkRepository.athletes()

        athletes shouldBeInstanceOf Either::class.java
        athletes.isLeft shouldEqual true
        athletes.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `athletes service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val athletes = networkRepository.athletes()

        athletes shouldBeInstanceOf Either::class.java
        athletes.isLeft shouldEqual true
        athletes.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `athletes request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val athletes = networkRepository.athletes()

        athletes shouldBeInstanceOf Either::class.java
        athletes.isLeft shouldEqual true
        athletes.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `should return empty athlete details by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { athleteDetailsResponse.body() }.willReturn(null)
        given { athleteDetailsResponse.isSuccessful }.willReturn(true)
        given { athleteDetailsCall.execute() }.willReturn(athleteDetailsResponse)
        given { service.athleteDetails(1) }.willReturn(athleteDetailsCall)

        val athleteDetails = networkRepository.athleteDetails(1)

        athleteDetails shouldEqual Either.Right(AthleteDetails.empty())
        verify(service).athleteDetails(1)
    }

    @Test fun `should get athlete details from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { athleteDetailsResponse.body() }.willReturn(
                AthleteDetailsEntity(
                    1,
                    String.empty(),
                    String.empty(),
                    String.empty(),
                    String.empty(),
                    String.empty(),
                    String.empty()
                ))
        given { athleteDetailsResponse.isSuccessful }.willReturn(true)
        given { athleteDetailsCall.execute() }.willReturn(athleteDetailsResponse)
        given { service.athleteDetails(1) }.willReturn(athleteDetailsCall)

        val athleteDetails = networkRepository.athleteDetails(1)

        athleteDetails shouldEqual Either.Right(
            AthleteDetails(
                1,
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty()
            )
        )
        verify(service).athleteDetails(1)
    }

    @Test fun `athlete details service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val athleteDetails = networkRepository.athleteDetails(1)

        athleteDetails shouldBeInstanceOf Either::class.java
        athleteDetails.isLeft shouldEqual true
        athleteDetails.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `athlete details service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(null)

        val athleteDetails = networkRepository.athleteDetails(1)

        athleteDetails shouldBeInstanceOf Either::class.java
        athleteDetails.isLeft shouldEqual true
        athleteDetails.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test fun `athlete details service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val athleteDetails = networkRepository.athleteDetails(1)

        athleteDetails shouldBeInstanceOf Either::class.java
        athleteDetails.isLeft shouldEqual true
        athleteDetails.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test fun `athlete details request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val athleteDetails = networkRepository.athleteDetails(1)

        athleteDetails shouldBeInstanceOf Either::class.java
        athleteDetails.isLeft shouldEqual true
        athleteDetails.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }
}