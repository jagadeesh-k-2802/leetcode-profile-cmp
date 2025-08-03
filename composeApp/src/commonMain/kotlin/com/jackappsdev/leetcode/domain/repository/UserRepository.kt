package com.jackappsdev.leetcode.domain.repository

import com.jackappsdev.leetcode.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User?>
    suspend fun saveUser(user: User)
    suspend fun clearUser()
}