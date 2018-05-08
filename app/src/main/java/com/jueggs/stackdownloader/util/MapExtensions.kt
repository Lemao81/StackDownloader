package com.jueggs.stackdownloader.util

import android.content.Context
import com.jueggs.andutils.extension.getStringArray
import com.jueggs.data.retrofit.dto.*
import com.jueggs.domain.model.SearchCriteria
import com.jueggs.jutils.*
import com.jueggs.stackdownloader.R
import com.jueggs.stackdownloader.bo.*

//data to model
fun <T> ItemShellDto<T>.mapToBo(): ItemShell<T> = ItemShell(
        items = items ?: emptyList(),
        hasMore = hasMore ?: false,
        quotaMax = quotaMax ?: INVALID_VALUE,
        quotaRemaining = quotaRemaining ?: INVALID_VALUE)

fun QuestionDto.mapToBo(): Question = Question(
        tagsList = tags ?: emptyList(),
        isAnswered = isAnswered ?: false,
        viewCount = viewCount ?: INVALID_VALUE,
        answerCount = answerCount ?: INVALID_VALUE,
        creationLabel = EMPTY_STRING,
        questionId = questionId ?: INVALID_VALUE_L,
        owner = owner?.mapToBo() ?: Owner.EMPTY,
        score = score ?: INVALID_VALUE,
        creationDate = creationDate ?: INVALID_VALUE_L,
        title = title ?: EMPTY_STRING,
        body = body ?: EMPTY_STRING,
        bodyMarkdown = bodyMarkdown ?: EMPTY_STRING,
        tagsLabel = EMPTY_STRING)

fun AnswerDto.mapToBo(): Answer = Answer(
        isAccepted = isAccepted ?: false,
        answerId = answerId ?: INVALID_VALUE_L,
        questionId = questionId ?: INVALID_VALUE_L,
        owner = owner?.mapToBo() ?: Owner.EMPTY,
        score = score ?: INVALID_VALUE,
        creationDate = creationDate ?: INVALID_VALUE_L,
        title = title ?: EMPTY_STRING,
        body = body ?: EMPTY_STRING,
        bodyMarkdown = bodyMarkdown ?: EMPTY_STRING,
        creationLabel = EMPTY_STRING)

fun OwnerDto.mapToBo(): Owner = Owner(
        userId = userId ?: INVALID_VALUE_L,
        reputation = reputation ?: INVALID_VALUE,
        profileImage = profileImage ?: EMPTY_STRING,
        displayName = displayName ?: EMPTY_STRING)


//model to entity
//fun Question.mapToEntity(): QuestionEntity {
//    val question = QuestionEntity()
//    question.tagsString = tagsList.join(";")
//    question.isAnswered = isAnswered
//    question.viewCount = viewCount
//    question.answerCount = answerCount
//    question.creationDate = creationDate
//    question.questionId = questionId
//    question.owner = owner.mapToEntity()
//    question.score = score
//    question.creationDate = creationDate
//    question.title = title
//    question.body = body
//    return question
//}
//
//fun Answer.mapToEntity(): AnswerEntity {
//    val answer = AnswerEntity()
//    answer.isAccepted = isAccepted
//    answer.answerId = answerId
//    answer.questionId = questionId
//    answer.owner = owner.mapToEntity()
//    answer.score = score
//    answer.creationDate = creationDate
//    answer.title = title
//    answer.body = body
//    return answer
//}
//
//fun Owner.mapToEntity(): OwnerEntity {
//    val owner = OwnerEntity()
//    owner.userId = userId
//    owner.reputation = reputation
//    owner.profileImage = profileImage
//    owner.displayName = displayName
//    return owner
//}


fun SearchCriteria.mapToQueryParameter(context: Context): QueryParameter = QueryParameter().also {
    it.limitTo = limitTo
    it.sort = getSortString(context, sortType)
    it.order = getOrderString(context, orderType)
    it.min = score
    it.tags = tags
}

fun getSortString(context: Context, index: Int) = context.getStringArray(R.array.sortType)[index]

fun getOrderString(context: Context, index: Int) = context.getStringArray(R.array.orderType)[index]