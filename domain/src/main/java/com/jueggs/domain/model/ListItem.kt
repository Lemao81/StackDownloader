package com.jueggs.domain.model

import java.util.*

abstract class ListItem(
        var score: Int? = null,
        var creationDate: Date? = null,
        var owner: Owner? = null,
        var title: String? = null,
        var body: String? = null,
        var bodyMarkdown: String? = null
)