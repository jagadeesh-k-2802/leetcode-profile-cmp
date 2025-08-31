package com.jackappsdev.leetcode.domain.repository

import com.jackappsdev.leetcode.domain.model.Friend
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    val friends: Flow<List<Friend>>
    suspend fun addOrUpdate(friend: Friend)
    suspend fun remove(username: String)
    suspend fun get(username: String): Friend?
}
