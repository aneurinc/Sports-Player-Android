package com.nsc9012.sportsplayer.features.login

import android.content.Context
import android.content.Intent
import com.nsc9012.sportsplayer.R
import com.nsc9012.sportsplayer.core.platform.BaseActivity

class LoginActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun fragment() = LoginFragment()

    override fun layoutId() = R.layout.activity_toolbar

}