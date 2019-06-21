
package com.aneurinc.sportsplayer.core.di

import com.aneurinc.sportsplayer.App
import com.aneurinc.sportsplayer.core.di.viewmodel.ViewModelModule
import com.aneurinc.sportsplayer.core.navigation.RouteActivity
import com.aneurinc.sportsplayer.features.athletes.AthletesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: App)
    fun inject(routeActivity: RouteActivity)
    fun inject(moviesFragment: AthletesFragment)
}
