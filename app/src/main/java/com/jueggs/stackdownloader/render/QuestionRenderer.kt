package com.jueggs.stackdownloader.render

import android.text.Html
import com.jueggs.andutils.helper.DateRenderer
import com.jueggs.domain.model.Question
import com.jueggs.stackdownloader.isNougatOrAbove

class QuestionRenderer(val dateRenderer: DateRenderer) : Renderer<Question> {
    override fun render(question: Question) {
        question.body = if (isNougatOrAbove()) Html.fromHtml(question.body, Html.FROM_HTML_MODE_COMPACT).toString() else Html.fromHtml(question.body).toString()
    }
}