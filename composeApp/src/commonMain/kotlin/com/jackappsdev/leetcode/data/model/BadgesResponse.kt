package com.jackappsdev.leetcode.data.model

import kotlinx.serialization.Serializable

// DTO for: GET https://alfa-leetcode-api.onrender.com/{username}/badges
@Serializable
data class BadgesResponse(
    val badges: List<BadgeItem> = emptyList(),
)

@Serializable
data class BadgeItem(
    val id: String,
    val displayName: String,
    val icon: String,
    val creationDate: String
)
