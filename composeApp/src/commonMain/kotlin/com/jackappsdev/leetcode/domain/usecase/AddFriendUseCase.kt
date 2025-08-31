package com.jackappsdev.leetcode.domain.usecase

import com.jackappsdev.leetcode.domain.model.Friend
import com.jackappsdev.leetcode.domain.repository.FriendsRepository
import kotlinx.coroutines.flow.first
import org.koin.core.annotation.Factory

@Factory
class AddFriendUseCase(
    private val friendsRepository: FriendsRepository,
    private val getCombinedProfile: GetCombinedProfileUseCase
) {
    suspend operator fun invoke(username: String): Result<Unit> {
        val res = getCombinedProfile(username).first()
        return res.mapCatching { profile ->
            val friend = Friend(
                username = profile.username,
                ranking = profile.ranking,
                problemsSolved = profile.totalSolved,
                avatarUrl = profile.avatarUrl
            )
            friendsRepository.addOrUpdate(friend)
        }
    }
}
