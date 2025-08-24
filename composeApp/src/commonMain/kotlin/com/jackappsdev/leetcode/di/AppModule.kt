package com.jackappsdev.leetcode.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jackappsdev.leetcode.constants.Constants.CSRF_TOKEN
import com.jackappsdev.leetcode.constants.Constants.CSRF_TOKEN_VALUE
import com.jackappsdev.leetcode.constants.Constants.HEADER_REFERER
import com.jackappsdev.leetcode.constants.Constants.HEADER_REFERRER_VALUE
import com.jackappsdev.leetcode.data.repository.LeetCodeRepositoryImpl
import com.jackappsdev.leetcode.data.repository.UserRepositoryImpl
import com.jackappsdev.leetcode.domain.repository.LeetCodeRepository
import com.jackappsdev.leetcode.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.request.cookie
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.jackappsdev.leetcode")
class AppModule {

    @Single
    fun provideDataStore(): DataStore<Preferences> = DataStoreFactory().getDataStore()

    @Single
    fun provideHttpClient(): HttpClient = HttpClient {
        install(DefaultRequest) {
            headers.append(HEADER_REFERER, HEADER_REFERRER_VALUE)
            cookie(CSRF_TOKEN, CSRF_TOKEN_VALUE)
        }

        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    @Single
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl

    @Single
    fun provideLeetCodeRepository(impl: LeetCodeRepositoryImpl): LeetCodeRepository = impl
}
