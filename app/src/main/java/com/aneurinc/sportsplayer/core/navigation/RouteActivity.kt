package com.aneurinc.sportsplayer.core.navigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aneurinc.sportsplayer.core.di.ApplicationComponent
import com.aneurinc.sportsplayer.core.extension.app
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
