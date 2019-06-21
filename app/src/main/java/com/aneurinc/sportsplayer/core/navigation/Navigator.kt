package com.aneurinc.sportsplayer.core.navigation

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.aneurinc.sportsplayer.features.login.Authenticator
import com.aneurinc.sportsplayer.features.login.LoginActivity
import com.aneurinc.sportsplayer.features.athletes.AthleteView
import com.aneurinc.sportsplayer.features.athletes.AthletesActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showMovies(context)
            false -> showLogin(context)
        }
    }

    private fun showMovies(context: Context) = context.startActivity(AthletesActivity.callingIntent(context))

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    fun showAthleteDetails(activity: FragmentActivity, movie: AthleteView, navigationExtras: Extras) {

    }

    class Extras(val transitionSharedElement: View)

}