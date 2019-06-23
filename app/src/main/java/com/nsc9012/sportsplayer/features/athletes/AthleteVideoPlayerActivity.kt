package com.nsc9012.sportsplayer.features.athletes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.nsc9012.sportsplayer.R
import com.nsc9012.sportsplayer.core.platform.BaseActivity

class AthleteVideoPlayerActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_VIDEO_URL = "INTENT_PARAM_VIDEO_URL"

        fun callingIntent(context: Context, videoUrl: String): Intent {
            val intent = Intent(context, AthleteVideoPlayerActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_VIDEO_URL, videoUrl)
            return intent
        }
    }

    override fun fragment() = AthleteVideoPlayerFragment.forVideo(intent.getStringExtra(INTENT_EXTRA_PARAM_VIDEO_URL))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun layoutId() = R.layout.activity_no_toolbar

}