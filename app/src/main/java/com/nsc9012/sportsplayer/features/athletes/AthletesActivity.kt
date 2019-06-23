package com.nsc9012.sportsplayer.features.athletes

import android.content.Context
import android.content.Intent
import com.nsc9012.sportsplayer.R
import com.nsc9012.sportsplayer.core.platform.BaseActivity

class AthletesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, AthletesActivity::class.java)
    }

    override fun fragment() = AthletesFragment()

    override fun layoutId() = R.layout.activity_toolbar

}