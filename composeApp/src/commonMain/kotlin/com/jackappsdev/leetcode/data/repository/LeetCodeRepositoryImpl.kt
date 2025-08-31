package com.jackappsdev.leetcode.data.repository

import com.jackappsdev.leetcode.constants.Constants.LARGE_RESPONSE_CACHE_TTL_MS
import com.jackappsdev.leetcode.constants.Constants.RESPONSE_CACHE_TTL_MS
import com.jackappsdev.leetcode.data.local.ResponseCache
import com.jackappsdev.leetcode.data.mapper.toDomain
import com.jackappsdev.leetcode.data.model.BadgesResponse
import com.jackappsdev.leetcode.data.model.LeetCodeFullProfileResponse
import com.jackappsdev.leetcode.data.model.LeetCodeProfileResponse
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.domain.repository.LeetCodeRepository
import com.jackappsdev.leetcode.utils.NotFoundException
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class LeetCodeRepositoryImpl(
    private val httpClient: HttpClient,
    private val responseCache: ResponseCache,
    private val json: Json
) : LeetCodeRepository {

    companion object {
        const val BASE_URL = "https://alfa-leetcode-api.onrender.com"
        val PROFILE_ENDPOINT: (String) -> String = { username -> "$BASE_URL/$username" }
        val FULL_PROFILE_ENDPOINT: (String) -> String = { username -> "$BASE_URL/userProfile/$username" }
        val BADGES_PROFILE_ENDPOINT: (String) -> String = { username -> "$BASE_URL/$username/badges" }
    }

    override suspend fun getProfile(username: String): Flow<Result<LeetCodeProfile>> = flow {
        try {
            val url = PROFILE_ENDPOINT(username)
            val cached = responseCache.get(url, ttlMillis = RESPONSE_CACHE_TTL_MS)
            val leetCodeResponse = if (cached != null) {
                json.decodeFromString(LeetCodeProfileResponse.serializer(), cached)
            } else {
                val response = httpClient.get(url)

                if (response.status == HttpStatusCode.NotFound) {
                    throw NotFoundException()
                }

                val txt = response.bodyAsText()
                responseCache.put(url, txt)
                json.decodeFromString(LeetCodeProfileResponse.serializer(), txt)
            }
            emit(Result.success(leetCodeResponse.toDomain()))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.failure(e))
        }
    }

    override suspend fun getFullProfile(username: String): Flow<Result<LeetCodeFullProfileResponse>> = flow {
        try {
            val url = FULL_PROFILE_ENDPOINT(username)
            val cached = responseCache.get(url, ttlMillis = RESPONSE_CACHE_TTL_MS)
            val fullProfile = if (cached != null) {
                json.decodeFromString(LeetCodeFullProfileResponse.serializer(), cached)
            } else {
                val response = httpClient.get(url)

                if (response.status == HttpStatusCode.NotFound) {
                    throw NotFoundException()
                }

                val txt = response.bodyAsText()
                responseCache.put(url, txt)
                json.decodeFromString(LeetCodeFullProfileResponse.serializer(), txt)
            }
            emit(Result.success(fullProfile))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.failure(e))
        }
    }

    override suspend fun getBadges(username: String): Flow<Result<BadgesResponse>> = flow {
        try {
            val url = BADGES_PROFILE_ENDPOINT(username)
            val cached = responseCache.get(url, ttlMillis = LARGE_RESPONSE_CACHE_TTL_MS)
            val fullProfile = if (cached != null) {
                json.decodeFromString(BadgesResponse.serializer(), cached)
            } else {
                val response = httpClient.get(url)

                if (response.status == HttpStatusCode.NotFound) {
                    throw NotFoundException()
                }

                val txt = response.bodyAsText()
                responseCache.put(url, txt)
                json.decodeFromString(BadgesResponse.serializer(), txt)
            }
            emit(Result.success(fullProfile))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.failure(e))
        }
    }
}
