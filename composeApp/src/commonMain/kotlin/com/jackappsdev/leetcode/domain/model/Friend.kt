package com.jackappsdev.leetcode.domain.model

import com.jackappsdev.leetcode.constants.Constants.EMPTY_STRING
import kotlinx.serialization.Serializable

@Serializable
data class Friend(
    val username: String,
    val ranking: Int,
    val problemsSolved: Int,
    val avatarUrl: String = EMPTY_STRING
)
