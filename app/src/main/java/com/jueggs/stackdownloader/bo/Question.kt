package com.jueggs.stackdownloader.bo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.jueggs.utils.*
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Question(
        var tagsList: List<String> = emptyList(),
        var isAnswered: Boolean = false,
        var viewCount: Int = INVALID_VALUE,
        var answerCount: Int = INVALID_VALUE,
        var tagsLabel: String = EMPTY_STRING,
        override var questionId: Long = INVALID_VALUE_L,
        override var owner: Owner = Owner.EMPTY,
        override var score: Int = INVALID_VALUE,
        override var creationDate: Long = INVALID_VALUE_L,
        override var title: String = EMPTY_STRING,
        override var body: String = EMPTY_STRING,
        override var bodyMarkdown: String = EMPTY_STRING,
        override var creationLabel: String = EMPTY_STRING) : ContentElement(questionId, owner, score, creationDate, title, body, bodyMarkdown, creationLabel), Parcelable {

    companion object {
        val EMPTY = Question()
    }
}