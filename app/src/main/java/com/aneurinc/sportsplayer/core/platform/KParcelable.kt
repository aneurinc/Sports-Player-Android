package com.aneurinc.sportsplayer.core.platform

import android.os.Parcel
import android.os.Parcelable

interface KParcelable : Parcelable {
    override fun describeContents() = 0
    override fun writeToParcel(dest: Parcel, flags: Int)
}

inline fun <reified T> parcelableCreator(crossinline create: (Parcel) -> T) =
    object : Parcelable.Creator<T> {
        override fun createFromParcel(source: Parcel) = create(source)
        override fun newArray(size: Int) = arrayOfNulls<T>(size)
    }