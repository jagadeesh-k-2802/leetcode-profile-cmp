package com.jackappsdev.leetcode.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeetCodeProfileResponse(
    val data: LeetCodeProfileData
)

@Serializable
data class LeetCodeProfileData(
    val matchedUser: MatchedUser
)

@Serializable
data class MatchedUser(
    @SerialName("contestBadge")
    val contestBadge: String? = null,
    val username: String,
    @SerialName("githubUrl")
    val githubUrl: String,
    @SerialName("twitterUrl")
    val twitterUrl: String,
    @SerialName("linkedinUrl")
    val linkedinUrl: String,
    val profile: Profile,
    @SerialName("submitStats")
    val submitStats: SubmitStatsDto? = null
)

@Serializable
data class Profile(
    val ranking: Int,
    @SerialName("userAvatar")
    val userAvatar: String,
    @SerialName("realName")
    val realName: String,
    @SerialName("aboutMe")
    val aboutMe: String,
    val school: String? = null,
    val websites: List<String>,
    @SerialName("countryName")
    val countryName: String,
    val company: String? = null,
    @SerialName("jobTitle")
    val jobTitle: String? = null,
    @SerialName("skillTags")
    val skillTags: List<String>,
    @SerialName("postViewCount")
    val postViewCount: Int,
    @SerialName("postViewCountDiff")
    val postViewCountDiff: Int,
    val reputation: Int,
    @SerialName("reputationDiff")
    val reputationDiff: Int,
    @SerialName("solutionCount")
    val solutionCount: Int,
    @SerialName("solutionCountDiff")
    val solutionCountDiff: Int,
    @SerialName("categoryDiscussCount")
    val categoryDiscussCount: Int,
    @SerialName("categoryDiscussCountDiff")
    val categoryDiscussCountDiff: Int,
    @SerialName("certificationLevel")
    val certificationLevel: String
)

@Serializable
data class SubmitStatsDto(
    val totalSubmissions: Int,
    val totalSolved: Int,
    val totalQuestions: Int,
    val totalAccepted: Int,
    val totalRejected: Int,
    val acRate: Double
)
