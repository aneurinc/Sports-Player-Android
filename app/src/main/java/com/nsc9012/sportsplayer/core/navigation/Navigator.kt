package com.nsc9012.sportsplayer.core.navigation

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.nsc9012.sportsplayer.features.login.Authenticator
import com.nsc9012.sportsplayer.features.login.LoginActivity
import com.nsc9012.sportsplayer.features.athletes.AthleteDetailsActivity
import com.nsc9012.sportsplayer.features.athletes.AthleteView
import com.nsc9012.sportsplayer.features.athletes.AthletesActivity
import com.nsc9012.sportsplayer.features.athletes.AthleteVideoPlayerActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showAthletes(context)
            false -> showLogin(context)
        }
    }

    private fun showAthletes(context: Context) = context.startActivity(AthletesActivity.callingIntent(context))

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    fun showAthleteDetails(activity: FragmentActivity, athleteView: AthleteView, navigationExtras: Extras) {
        val intent = AthleteDetailsActivity.callingIntent(activity, athleteView)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    fun showVideoPlayer(activity: FragmentActivity, videoUrl: String) {
        val intent = AthleteVideoPlayerActivity.callingIntent(activity, videoUrl)
        activity.startActivity(intent)
    }

    class Extras(val transitionSharedElement: View)

}