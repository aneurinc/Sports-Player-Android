package com.aneurinc.sportsplayer.features.athletes

import android.content.Context
import android.content.Intent
import com.aneurinc.sportsplayer.R
import com.aneurinc.sportsplayer.core.platform.BaseActivity

class AthleteDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_ATHLETE = "INTENT_PARAM_ATHLETE"

        fun callingIntent(context: Context, athlete: AthleteView): Intent {
            val intent = Intent(context, AthleteDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_ATHLETE, athlete)
            return intent
        }
    }

    override fun fragment() = AthleteDetailsFragment.forAthlete(intent.getParcelableExtra(INTENT_EXTRA_PARAM_ATHLETE))

    override fun layoutId() = R.layout.activity_toolbar

}