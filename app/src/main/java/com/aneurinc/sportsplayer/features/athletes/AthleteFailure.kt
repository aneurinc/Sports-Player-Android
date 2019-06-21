package com.aneurinc.sportsplayer.features.athletes

import com.aneurinc.sportsplayer.core.exception.Failure

class AthleteFailure {
    class ListNotAvailable: Failure.FeatureFailure()
    class NonExistentMovie: Failure.FeatureFailure()
}