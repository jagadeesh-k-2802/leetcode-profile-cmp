package com.jackappsdev.leetcode.data.mapper

import com.jackappsdev.leetcode.data.model.LeetCodeProfileResponse
import com.jackappsdev.leetcode.data.model.SubmitStatsDto
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.domain.model.SubmissionStats

fun LeetCodeProfileResponse.toDomain(): LeetCodeProfile {
    val matchedUser = data.matchedUser
    val profile = matchedUser.profile
    
    return LeetCodeProfile(
        username = matchedUser.username,
        name = profile.realName,
        totalSolvedProblems = matchedUser.submitStats?.totalSolved ?: 0,
        rating = 0, // Not available in current schema
        ranking = profile.ranking,
        reputation = profile.reputation,
        submissionStats = matchedUser.submitStats?.toDomain() ?: SubmissionStats(),
        avatarUrl = profile.userAvatar,
        githubUrl = matchedUser.githubUrl,
        linkedinUrl = matchedUser.linkedinUrl,
        twitterUrl = matchedUser.twitterUrl,
        countryName = profile.countryName,
        aboutMe = profile.aboutMe,
        websites = profile.websites,
        solutionCount = profile.solutionCount,
        skillTags = profile.skillTags
    )
}

fun SubmitStatsDto.toDomain(): SubmissionStats {
    return SubmissionStats(
        totalSubmissions = totalSubmissions,
        totalSolved = totalSolved,
        totalQuestions = totalQuestions,
        totalAccepted = totalAccepted,
        totalRejected = totalRejected,
        acRate = acRate
    )
}
