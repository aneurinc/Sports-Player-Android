package com.aneurinc.sportsplayer.features.athletes

import android.content.Context
import android.content.Intent
import com.aneurinc.sportsplayer.R
import com.aneurinc.sportsplayer.core.platform.BaseActivity

class AthletesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, AthletesActivity::class.java)
    }

    override fun fragment() = AthletesFragment()

    override fun layoutId() = R.layout.activity_toolbar

}