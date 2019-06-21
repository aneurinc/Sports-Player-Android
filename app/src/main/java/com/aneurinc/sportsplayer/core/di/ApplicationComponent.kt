
package com.aneurinc.sportsplayer.core.di

import com.aneurinc.sportsplayer.App
import com.aneurinc.sportsplayer.core.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: App)
}
