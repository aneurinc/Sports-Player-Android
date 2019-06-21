package com.aneurinc.sportsplayer.core.platform

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aneurinc.sportsplayer.R
import com.aneurinc.sportsplayer.core.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) = savedInstanceState ?: supportFragmentManager.inTransaction {
        add(R.id.fragmentContainer, fragment())
    }

    abstract fun fragment(): BaseFragment

    abstract fun layoutId(): Int
}