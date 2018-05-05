package com.jueggs.stackdownloader.util

import com.jueggs.stackdownloader.bo.*
import com.jueggs.stackdownloader.retrofit.dto.*
import com.jueggs.utils.*

//data to model
fun <T> ItemShellData<T>.mapToBo(): ItemShell<T> = ItemShell(
        items = items ?: emptyList(),
        hasMore = has_more ?: false,
        quotaMax = quota_max ?: INVALID_VALUE,
        quotaRemaining = quota_remaining ?: INVALID_VALUE)

fun QuestionData.mapToBo(): Question = Question(
        tagsList = tags ?: emptyList(),
        isAnswered = is_answered ?: false,
        viewCount = view_count ?: INVALID_VALUE,
        answerCount = answer_count ?: INVALID_VALUE,
        creationLabel = EMPTY_STRING,
        questionId = question_id ?: INVALID_VALUE_L,
        owner = owner?.mapToBo() ?: Owner.EMPTY,
        score = score ?: INVALID_VALUE,
        creationDate = creation_date ?: INVALID_VALUE_L,
        title = title ?: EMPTY_STRING,
        body = body ?: EMPTY_STRING,
        bodyMarkdown = body_markdown ?: EMPTY_STRING,
        tagsLabel = EMPTY_STRING)

fun AnswerData.mapToBo(): Answer = Answer(
        isAccepted = accepted ?: false,
        answerId = answer_id ?: INVALID_VALUE_L,
        questionId = question_id ?: INVALID_VALUE_L,
        owner = owner?.mapToBo() ?: Owner.EMPTY,
        score = score ?: INVALID_VALUE,
        creationDate = creation_date ?: INVALID_VALUE_L,
        title = title ?: EMPTY_STRING,
        body = body ?: EMPTY_STRING,
        bodyMarkdown = body_markdown ?: EMPTY_STRING,
        creationLabel = EMPTY_STRING)

fun OwnerData.mapToBo(): Owner = Owner(
        userId = user_id ?: INVALID_VALUE_L,
        reputation = reputation ?: INVALID_VALUE,
        profileImage = profile_image ?: EMPTY_STRING,
        displayName = display_name ?: EMPTY_STRING)


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

fun SearchCriteria.mapToQueryParameter(): QueryParameter.Builder = QueryParameter.Builder()
        .pagesize(pageSize)
        .sort(sortOrder)
        .order(orderType)
        .tags(tags)
        .min(minScore)