package com.jackappsdev.leetcode.domain.model

data class LeetCodeProfile(
    val username: String,
    val name: String,
    val totalSolvedProblems: Int,
    val rating: Int,
    val ranking: Int,
    val reputation: Int,
    val submissionStats: SubmissionStats,
    val avatarUrl: String,
    val githubUrl: String,
    val linkedinUrl: String,
    val twitterUrl: String,
    val countryName: String,
    val aboutMe: String,
    val websites: List<String>,
    val solutionCount: Int,
    val skillTags: List<String>
)

data class SubmissionStats(
    val totalSubmissions: Int = 0,
    val totalSolved: Int = 0,
    val totalQuestions: Int = 0,
    val totalAccepted: Int = 0,
    val totalRejected: Int = 0,
    val acRate: Double = 0.0
)
