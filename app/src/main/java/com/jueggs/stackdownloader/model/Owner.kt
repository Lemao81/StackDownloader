package com.jueggs.stackdownloader.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
class Owner(
        val reputation: Int,
        val userId: Int,
        val userType: String,
        val profileImage: String,
        val displayName: String,
        val link: String) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelOwner.CREATOR
    }
}