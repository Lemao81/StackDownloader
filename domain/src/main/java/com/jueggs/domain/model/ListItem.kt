package com.jueggs.domain.model

import org.joda.time.DateTime

abstract class ListItem(
    var score: Int? = null,
    var creationDateTime: DateTime? = null,
    var owner: Owner? = null,
    var title: String? = null,
    var body: String? = null,
    var bodyMarkdown: String? = null
)