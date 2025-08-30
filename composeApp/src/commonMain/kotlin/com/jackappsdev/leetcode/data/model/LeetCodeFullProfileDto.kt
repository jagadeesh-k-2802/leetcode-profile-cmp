package com.jackappsdev.leetcode.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// DTO for: GET https://alfa-leetcode-api.onrender.com/userProfile/{username}
@Serializable
data class LeetCodeFullProfileResponse(
    val totalSolved: Int,
    val totalSubmissions: List<DifficultySubmission>,
    val totalQuestions: Int,
    val easySolved: Int,
    val totalEasy: Int,
    val mediumSolved: Int,
    val totalMedium: Int,
    val hardSolved: Int,
    val totalHard: Int,
    val ranking: Int,
    val contributionPoint: Int,
    val reputation: Int,
    val submissionCalendar: Map<String, Int> = emptyMap(),
    val recentSubmissions: List<RecentSubmission> = emptyList(),
    val matchedUserStats: MatchedUserStats
)

@Serializable
data class DifficultySubmission(
    val difficulty: String,
    val count: Int,
    val submissions: Int
)

@Serializable
data class RecentSubmission(
    val title: String,
    val titleSlug: String,
    val timestamp: String,
    val statusDisplay: String,
    val lang: String,
    @SerialName("__typename")
    val typename: String? = null
)

@Serializable
data class MatchedUserStats(
    val acSubmissionNum: List<CategorySubmission>,
    val totalSubmissionNum: List<CategorySubmission>
)

@Serializable
data class CategorySubmission(
    val difficulty: String,
    val count: Int,
    val submissions: Int
)
