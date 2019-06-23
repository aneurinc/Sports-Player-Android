
package com.nsc9012.sportsplayer.core.di

import com.nsc9012.sportsplayer.App
import com.nsc9012.sportsplayer.core.di.viewmodel.ViewModelModule
import com.nsc9012.sportsplayer.core.navigation.RouteActivity
import com.nsc9012.sportsplayer.features.athletes.AthleteDetailsFragment
import com.nsc9012.sportsplayer.features.athletes.AthletesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: App)
    fun inject(routeActivity: RouteActivity)
    fun inject(athletesFragment: AthletesFragment)
    fun inject(athleteDetailsFragment: AthleteDetailsFragment)
}
