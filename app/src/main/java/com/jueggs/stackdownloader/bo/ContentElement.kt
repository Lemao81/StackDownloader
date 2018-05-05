package com.jueggs.stackdownloader.bo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.jueggs.utils.*
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
open class ContentElement(
        open var questionId: Long = INVALID_VALUE_L,
        open var owner: Owner = Owner.EMPTY,
        open var score: Int = INVALID_VALUE,
        open var creationDate: Long = INVALID_VALUE_L,
        open var title: String = EMPTY_STRING,
        open var body: String = EMPTY_STRING,
        open var bodyMarkdown: String = EMPTY_STRING,
        open var creationLabel: String = EMPTY_STRING) : Parcelable