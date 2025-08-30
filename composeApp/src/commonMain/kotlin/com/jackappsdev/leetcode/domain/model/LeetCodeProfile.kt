package com.jackappsdev.leetcode.domain.model

// Domain model for combined LeetCode profile data
data class LeetCodeProfile(
    val username: String,
    val name: String,
    val avatarUrl: String,
    val githubUrl: String,
    val linkedinUrl: String,
    val twitterUrl: String,
    val countryName: String,
    val aboutMe: String,
    val websites: List<String>,
    val solutionCount: Int,
    val skillTags: List<String>,
    val submissionCalendar: Map<String, Int> = emptyMap(),
    val totalSolved: Int,
    val totalQuestions: Int,
    val easySolved: Int,
    val totalEasy: Int,
    val mediumSolved: Int,
    val totalMedium: Int,
    val hardSolved: Int,
    val totalHard: Int,
    val rating: Int,
    val ranking: Int,
    val reputation: Int,
)
