package com.jackappsdev.leetcode.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// DTO for: GET https://alfa-leetcode-api.onrender.com/{username}
@Serializable
data class LeetCodeProfileResponse(
    val username: String,
    val name: String,
    val birthday: String? = null,
    val avatar: String,
    val ranking: Int,
    val reputation: Int,
    @SerialName("gitHub")
    val gitHub: String? = null,
    val twitter: String? = null,
    @SerialName("linkedIN")
    val linkedIN: String? = null,
    val website: List<String> = emptyList(),
    val country: String? = null,
    val company: String? = null,
    val school: String? = null,
    val skillTags: List<String> = emptyList(),
    @SerialName("about")
    val about: String? = null
)
