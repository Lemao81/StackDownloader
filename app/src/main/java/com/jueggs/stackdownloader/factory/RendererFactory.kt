package com.jueggs.stackdownloader.factory

import com.jueggs.stackdownloader.bo.*
import com.jueggs.stackdownloader.render.*
import com.jueggs.utils.DateRenderer
import java.security.InvalidParameterException

class RendererFactory(dateRenderer: DateRenderer) {
    val questionRenderer = QuestionRenderer(dateRenderer)
    val answerRenderer = AnswerRenderer(dateRenderer)

    inline fun <reified T> createRenderer(): Renderer<T> =
            when (T::class) {
                Question::class -> questionRenderer as Renderer<T>
                Answer::class -> answerRenderer as Renderer<T>
                else -> throw InvalidParameterException("there is no renderer defined for type ${T::class.java.simpleName}")
            }
}