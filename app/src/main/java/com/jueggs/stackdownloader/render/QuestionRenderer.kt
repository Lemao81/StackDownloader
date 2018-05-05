package com.jueggs.stackdownloader.render

import android.text.Html
import com.jueggs.stackdownloader.bo.Question
import com.jueggs.stackdownloader.util.isNougatOrAbove
import com.jueggs.utils.DateRenderer
import com.jueggs.utils.extension.join

class QuestionRenderer(val dateRenderer: DateRenderer) : Renderer<Question> {
    override fun render(question: Question) {
        question.tagsLabel = question.tagsList.join(", ")
        question.body = if (isNougatOrAbove()) Html.fromHtml(question.body, Html.FROM_HTML_MODE_COMPACT).toString() else Html.fromHtml(question.body).toString()
        question.creationLabel = dateRenderer.render(question.creationDate)
    }
}