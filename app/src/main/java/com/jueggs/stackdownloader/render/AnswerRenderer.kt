package com.jueggs.stackdownloader.render

import android.text.Html
import com.jueggs.andutils.helper.DateRenderer
import com.jueggs.domain.model.Answer
import com.jueggs.stackdownloader.isNougatOrAbove

class AnswerRenderer(val dateRenderer: DateRenderer) : Renderer<Answer> {
    override fun render(answer: Answer) {
        answer.body = if (isNougatOrAbove()) Html.fromHtml(answer.body, Html.FROM_HTML_MODE_COMPACT).toString() else Html.fromHtml(answer.body).toString()
    }
}