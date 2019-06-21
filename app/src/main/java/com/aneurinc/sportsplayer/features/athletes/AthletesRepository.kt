package com.aneurinc.sportsplayer.features.athletes

import com.aneurinc.sportsplayer.core.exception.Failure
import com.aneurinc.sportsplayer.core.exception.Failure.*
import com.aneurinc.sportsplayer.core.functional.Either
import com.aneurinc.sportsplayer.core.functional.Either.*
import com.aneurinc.sportsplayer.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface AthletesRepository {

    fun athletes(): Either<Failure, List<Athlete>>
    fun athleteDetails(id: Int): Either<Failure, AthleteDetails>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: AthletesService
    ) : AthletesRepository {

        override fun athletes(): Either<Failure, List<Athlete>> {
            return when (networkHandler.isConnected) {
                true -> request(service.athletes(), { it.map { it.toAthlete() } }, emptyList())
                false, null -> Left(NetworkConnection)
            }
        }

        override fun athleteDetails(id: Int): Either<Failure, AthleteDetails> {
            return when (networkHandler.isConnected) {
                true -> request(service.athleteDetails(id), { it.toAthleteDetails() }, AthleteDetailsEntity.empty())
                false, null -> Left(NetworkConnection)
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError)
                }
            } catch (exception: Throwable) {
                Left(ServerError)
            }
        }
    }
}