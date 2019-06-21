package com.aneurinc.sportsplayer.features.athletes

import android.os.Parcel
import com.aneurinc.sportsplayer.core.platform.KParcelable
import com.aneurinc.sportsplayer.core.platform.parcelableCreator

data class AthleteView(val id: Int, val pictureUrl: String) : KParcelable {

    companion object {
        @JvmField val CREATOR = parcelableCreator(::AthleteView)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(pictureUrl)
        }
    }

}