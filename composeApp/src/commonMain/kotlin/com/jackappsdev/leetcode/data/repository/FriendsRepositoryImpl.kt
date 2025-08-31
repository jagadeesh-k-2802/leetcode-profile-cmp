package com.jackappsdev.leetcode.data.repository

import com.jackappsdev.leetcode.data.local.FriendsLocalStore
import com.jackappsdev.leetcode.domain.model.Friend
import com.jackappsdev.leetcode.domain.repository.FriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.koin.core.annotation.Single

@Single
class FriendsRepositoryImpl(
    private val local: FriendsLocalStore
) : FriendsRepository {
    override val friends: Flow<List<Friend>> = local.friends

    override suspend fun addOrUpdate(friend: Friend) {
        val current = local.friends.first().toMutableList()
        val idx = current.indexOfFirst { it.username == friend.username }
        if (idx >= 0) current[idx] = friend else current.add(friend)
        local.save(current)
    }

    override suspend fun remove(username: String) {
        val updated = local.friends.first().filterNot { it.username == username }
        local.save(updated)
    }

    override suspend fun get(username: String): Friend? {
        return local.friends.first().firstOrNull { it.username == username }
    }
}
