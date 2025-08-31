package com.jackappsdev.leetcode.data.mapper

import com.jackappsdev.leetcode.constants.Constants.EMPTY_STRING
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
        githubUrl = gitHub ?: EMPTY_STRING,
        linkedinUrl = linkedIN ?: EMPTY_STRING,
        twitterUrl = twitter ?: EMPTY_STRING,
        countryName = country ?: EMPTY_STRING,
        aboutMe = about ?: EMPTY_STRING,
        websites = website,
        solutionCount = 0,
        skillTags = skillTags
    )
}
