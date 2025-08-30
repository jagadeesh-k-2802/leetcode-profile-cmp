package com.jackappsdev.leetcode.domain.repository

import com.jackappsdev.leetcode.data.model.LeetCodeFullProfileResponse
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import kotlinx.coroutines.flow.Flow

interface LeetCodeRepository {
    suspend fun getProfile(username: String): Flow<Result<LeetCodeProfile>>
    suspend fun getFullProfile(username: String): Flow<Result<LeetCodeFullProfileResponse>>
}
