package com.jueggs.stackdownloader.model.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class SearchCriteriaDto(
        val limitTo: String,
        val score: String,
        val orderType: Int,
        val sortType: Int,
        val tags: List<String>?
) : Parcelable