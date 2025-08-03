package com.jackappsdev.leetcode.data.repository

import com.jackappsdev.leetcode.data.local.UserPreferences
import com.jackappsdev.leetcode.domain.model.User
import com.jackappsdev.leetcode.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class UserRepositoryImpl(
    private val userPreferences: UserPreferences
) : UserRepository {
    override fun getUser(): Flow<User?> = userPreferences.username.map { username ->
        username?.let { User(it) }
    }

    override suspend fun saveUser(user: User) {
        userPreferences.saveUsername(user.username)
    }

    override suspend fun clearUser() {
        userPreferences.clearUsername()
    }
}