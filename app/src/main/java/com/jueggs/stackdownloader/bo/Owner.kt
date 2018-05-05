package com.jueggs.stackdownloader.bo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.jueggs.utils.*
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Owner(
        var userId: Long = INVALID_VALUE_L,
        var reputation: Int = INVALID_VALUE,
        var profileImage: String = EMPTY_STRING,
        var displayName: String = EMPTY_STRING) : Parcelable {

    companion object {
        val EMPTY = Owner()
    }
}