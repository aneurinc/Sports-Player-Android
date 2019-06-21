package com.aneurinc.sportsplayer

import android.app.Application
import com.aneurinc.sportsplayer.core.di.ApplicationComponent
import com.aneurinc.sportsplayer.core.di.ApplicationModule
import com.aneurinc.sportsplayer.core.di.DaggerApplicationComponent

class App : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}