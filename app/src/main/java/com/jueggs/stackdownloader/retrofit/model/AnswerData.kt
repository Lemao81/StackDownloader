package com.jueggs.stackdownloader.retrofit.model

class AnswerData(
        var down_vote_count: Int? = null,
        var up_vote_count: Int? = null,
        var accepted: Boolean? = null,
        var answer_id: Long? = null) : ContentElementData()