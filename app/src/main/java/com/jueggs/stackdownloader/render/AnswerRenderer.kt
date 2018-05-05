package com.jueggs.stackdownloader.render

import android.text.Html
import com.jueggs.stackdownloader.bo.Answer
import com.jueggs.stackdownloader.util.isNougatOrAbove
import com.jueggs.utils.DateRenderer

class AnswerRenderer(val dateRenderer: DateRenderer) : Renderer<Answer> {
    override fun render(answer: Answer) {
        answer.body = if (isNougatOrAbove()) Html.fromHtml(answer.body, Html.FROM_HTML_MODE_COMPACT).toString() else Html.fromHtml(answer.body).toString()
        answer.creationLabel = dateRenderer.render(answer.creationDate)
    }
}