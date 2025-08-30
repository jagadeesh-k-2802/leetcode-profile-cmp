package com.jackappsdev.leetcode.domain.usecase

import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.domain.repository.LeetCodeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class GetCombinedProfileUseCase(
    private val repository: LeetCodeRepository
) {

    operator fun invoke(username: String): Flow<Result<LeetCodeProfile>> = flow {
        try {
            val profileResult = repository.getProfile(username).first()
            val fullResult = repository.getFullProfile(username).first()

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
                ranking = full.ranking,
                reputation = full.reputation
            )
            emit(Result.success(combined))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.failure(e))
        }
    }
}
