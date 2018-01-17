package com.jueggs.stackdownloader.utils

import com.jueggs.stackdownloader.model.*
import com.jueggs.utils.EMPTY_STRING
import com.jueggs.utils.INVALID_VALUE
import com.jueggs.utils.INVALID_VALUE_L

fun <T> ItemShellData<T>.mapNullSafe(): ItemShell<T> = ItemShell(
        items ?: arrayListOf(),
        has_more ?: false,
        quota_max ?: INVALID_VALUE,
        quota_remaining ?: INVALID_VALUE)

fun QuestionData.mapNullSafe(): Question = Question(
        tags ?: arrayListOf(),
        is_answered ?: false,
        view_count ?: INVALID_VALUE,
        answer_count ?: INVALID_VALUE,
        EMPTY_STRING, EMPTY_STRING,
        question_id ?: INVALID_VALUE_L,
        owner ?: Owner(INVALID_VALUE, INVALID_VALUE, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING),
        score ?: INVALID_VALUE,
        last_activity_date ?: INVALID_VALUE_L,
        last_edit_date ?: INVALID_VALUE_L,
        creation_date ?: INVALID_VALUE_L,
        link ?: EMPTY_STRING,
        title ?: EMPTY_STRING,
        body ?: EMPTY_STRING,
        body_markdown ?: EMPTY_STRING,
        EMPTY_STRING, EMPTY_STRING, Any())

fun AnswerData.mapNullSafe(): Answer = Answer(
        down_vote_count ?: INVALID_VALUE,
        up_vote_count ?: INVALID_VALUE,
        accepted ?: false,
        answer_id ?: INVALID_VALUE_L,
        question_id ?: INVALID_VALUE_L,
        owner ?: Owner(INVALID_VALUE, INVALID_VALUE, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING),
        score ?: INVALID_VALUE,
        last_activity_date ?: INVALID_VALUE_L,
        last_edit_date ?: INVALID_VALUE_L,
        creation_date ?: INVALID_VALUE_L,
        link ?: EMPTY_STRING,
        title ?: EMPTY_STRING,
        body ?: EMPTY_STRING,
        body_markdown ?: EMPTY_STRING,
        EMPTY_STRING, EMPTY_STRING, Any())

fun SearchCriteria.mapToQueryParameter(): QueryParameter.Builder = QueryParameter.Builder()
        .pagesize(pageSize)
        .sort(sortOrder)
        .order(orderType)
        .tags(tags)
        .min(minScore)