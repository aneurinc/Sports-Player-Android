package com.nsc9012.sportsplayer.core.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.nsc9012.sportsplayer.core.exception.Failure

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))