package com.aneurinc.sportsplayer.core.extension

import android.app.Activity
import com.aneurinc.sportsplayer.App

val Activity.app: App get() = application as App