package com.nsc9012.sportsplayer

import android.app.Application
import com.nsc9012.sportsplayer.core.di.ApplicationComponent
import com.nsc9012.sportsplayer.core.di.ApplicationModule
import com.nsc9012.sportsplayer.core.di.DaggerApplicationComponent

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