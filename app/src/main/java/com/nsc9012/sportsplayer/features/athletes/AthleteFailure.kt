package com.nsc9012.sportsplayer.features.athletes

import com.nsc9012.sportsplayer.core.exception.Failure

class AthleteFailure {
    class ListNotAvailable: Failure.FeatureFailure()
    class AthleteNotFound: Failure.FeatureFailure()
}