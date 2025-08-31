package com.jackappsdev.leetcode.domain.usecase

import com.jackappsdev.leetcode.constants.Constants.LEETCODE_ASSETS_BASE_URL
import com.jackappsdev.leetcode.constants.Constants.LEETCODE_BASE_URL
import com.jackappsdev.leetcode.data.model.BadgesResponse
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.domain.repository.LeetCodeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class GetCombinedProfileUseCase(
    private val repository: LeetCodeRepository
) {

    operator fun invoke(
        username: String,
        config: GetCombinedProfileConfig = GetCombinedProfileConfig()
    ): Flow<Result<LeetCodeProfile>> = flow {
        try {
            val (profileResult, fullResult, badges) = coroutineScope {
                val a = async { repository.getProfile(username).first() }
                val b = async { repository.getFullProfile(username).first() }
                val c = async { if (config.includeBadges) repository.getBadges(username).first() else null }
                Triple(a.await(), b.await(), c.await())
            }

            if (profileResult.isFailure) {
                emit(Result.failure(profileResult.exceptionOrNull()!!))
                return@flow
            }

            // Still show minimal profile if available
            if (fullResult.isFailure) {
                emit(profileResult)
                return@flow
            }

            val base = profileResult.getOrThrow()
            val full = fullResult.getOrThrow()

            val combined = base.copy(
                totalSolved = full.totalSolved,
                totalQuestions = full.totalQuestions,
                easySolved = full.easySolved,
                totalEasy = full.totalEasy,
                mediumSolved = full.mediumSolved,
                totalMedium = full.totalMedium,
                hardSolved = full.hardSolved,
                totalHard = full.totalHard,
                submissionCalendar = full.submissionCalendar,
                badges = parseBadges(badges?.getOrNull()),
                ranking = full.ranking,
                reputation = full.reputation
            )
            emit(Result.success(combined))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.failure(e))
        }
    }

    private fun parseBadges(badgesResponse: BadgesResponse?): List<String> {
        return badgesResponse?.badges?.map { badge ->
            if (badge.icon.startsWith(LEETCODE_ASSETS_BASE_URL)) {
                badge.icon
            } else {
                "${LEETCODE_BASE_URL}${badge.icon}"
            }
        } ?: emptyList()
    }
}

data class GetCombinedProfileConfig(
    val includeBadges: Boolean = false
)
