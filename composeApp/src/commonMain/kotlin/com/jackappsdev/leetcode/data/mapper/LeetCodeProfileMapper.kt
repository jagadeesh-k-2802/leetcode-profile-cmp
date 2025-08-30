package com.jackappsdev.leetcode.data.mapper

import com.jackappsdev.leetcode.data.model.LeetCodeProfileResponse
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile

fun LeetCodeProfileResponse.toDomain(): LeetCodeProfile {
    return LeetCodeProfile(
        username = username,
        name = name,
        totalSolved = 0,
        totalQuestions = 0,
        easySolved = 0,
        totalEasy = 0,
        mediumSolved = 0,
        totalMedium = 0,
        hardSolved = 0,
        totalHard = 0,
        rating = 0,
        ranking = ranking,
        reputation = reputation,
        avatarUrl = avatar,
        githubUrl = gitHub ?: "",
        linkedinUrl = linkedIN ?: "",
        twitterUrl = twitter ?: "",
        countryName = country ?: "",
        aboutMe = about ?: "",
        websites = website,
        solutionCount = 0,
        skillTags = skillTags
    )
}
