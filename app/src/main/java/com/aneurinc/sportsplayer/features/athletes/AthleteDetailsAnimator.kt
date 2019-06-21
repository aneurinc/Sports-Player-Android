package com.aneurinc.sportsplayer.features.athletes

import android.support.v4.app.FragmentActivity
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import com.aneurinc.sportsplayer.core.extension.cancelTransition
import javax.inject.Inject

class AthleteDetailsAnimator
@Inject constructor() {

    companion object {
        const val TRANSITION_DELAY = 200L
        const val TRANSITION_DURATION = 400L
        const val SCALE_UP_VALUE = 1.0F
        const val SCALE_UP_DURATION = 400L
        const val SCALE_DOWN_VALUE = 0.0F
        const val SCALE_DOWN_DURATION = 200L
    }

    internal fun postponeEnterTransition(activity: FragmentActivity) = activity.postponeEnterTransition()
    internal fun cancelTransition(view: View) = view.cancelTransition()

    internal fun scaleUpView(view: View) = scaleView(view, SCALE_UP_VALUE, SCALE_UP_VALUE, SCALE_UP_DURATION)
    internal fun scaleDownView(view: View) = scaleView(view, SCALE_DOWN_VALUE, SCALE_DOWN_VALUE, SCALE_DOWN_DURATION)

    internal fun fadeVisible(viewContainer: ViewGroup, view: View) =
        beginTransitionFor(viewContainer, view, View.VISIBLE)

    internal fun fadeInvisible(viewContainer: ViewGroup, view: View) =
        beginTransitionFor(viewContainer, view, View.INVISIBLE)

    private fun scaleView(view: View, x: Float, y: Float, duration: Long) =
        view.animate()
            .scaleX(x)
            .scaleY(y)
            .setDuration(duration)
            .setInterpolator(FastOutSlowInInterpolator())
            .withLayer()
            .setListener(null)
            .start()

    private fun beginTransitionFor(viewContainer: ViewGroup, view: View, visibility: Int) {
        val transition = Fade()
        transition.startDelay = TRANSITION_DELAY
        transition.duration = TRANSITION_DURATION
        TransitionManager.beginDelayedTransition(viewContainer, transition)
        view.visibility = visibility
    }
}