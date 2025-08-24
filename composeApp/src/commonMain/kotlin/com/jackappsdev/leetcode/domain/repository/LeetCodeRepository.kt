package com.jackappsdev.leetcode.domain.repository

import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import kotlinx.coroutines.flow.Flow

interface LeetCodeRepository {
    suspend fun getProfile(username: String): Flow<Result<LeetCodeProfile>>
}
