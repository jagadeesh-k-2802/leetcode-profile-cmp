package com.jackappsdev.leetcode.data.repository

import com.jackappsdev.leetcode.constants.Constants.BASE_URL
import com.jackappsdev.leetcode.constants.Constants.QUERY
import com.jackappsdev.leetcode.constants.Constants.USERNAME
import com.jackappsdev.leetcode.constants.Constants.VARIABLES
import com.jackappsdev.leetcode.data.mapper.toDomain
import com.jackappsdev.leetcode.data.model.LeetCodeProfileResponse
import com.jackappsdev.leetcode.data.queries.GraphQL.USER_PROFILE_QUERY
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.domain.repository.LeetCodeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.buildJsonObject
import org.koin.core.annotation.Single

@Single
class LeetCodeRepositoryImpl(
    private val httpClient: HttpClient
) : LeetCodeRepository {

    override suspend fun getProfile(username: String): Flow<Result<LeetCodeProfile>> = flow {
        try {
            val response = httpClient.post(BASE_URL) {
                contentType(ContentType.Application.Json)
                setBody(
                    buildJsonObject {
                        QUERY to USER_PROFILE_QUERY
                        VARIABLES to mapOf(USERNAME to username)
                    }
                )
            }

            println(response.body<String>())
            val leetCodeResponse = response.body<LeetCodeProfileResponse>()
            emit(Result.success(leetCodeResponse.toDomain()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
