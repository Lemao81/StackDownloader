package com.jueggs.stackdownloader.models

import com.jueggs.stackdownloader.utils.EMPTY_STRING
import com.jueggs.stackdownloader.utils.INVALID_VALUE

class Owner(val reputation: Int, val user_id: Int, val user_type: String, val profile_image: String, val display_name: String, val link: String) {
    constructor() : this(INVALID_VALUE, INVALID_VALUE, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING)
}