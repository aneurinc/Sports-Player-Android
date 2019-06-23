package com.nsc9012.sportsplayer.core.navigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nsc9012.sportsplayer.core.di.ApplicationComponent
import com.nsc9012.sportsplayer.core.extension.app
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        app.appComponent
    }

    @Inject internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }

}
