package com.jackappsdev.leetcode.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jackappsdev.leetcode.data.repository.LeetCodeRepositoryImpl
import com.jackappsdev.leetcode.data.repository.UserRepositoryImpl
import com.jackappsdev.leetcode.domain.repository.LeetCodeRepository
import com.jackappsdev.leetcode.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.contentType
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
    fun provideJson(): Json = Json {
        prettyPrint = false
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Single
    fun provideHttpClient(json: Json): HttpClient = HttpClient {
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(json)
        }
    }

    @Single
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl

    @Single
    fun provideLeetCodeRepository(impl: LeetCodeRepositoryImpl): LeetCodeRepository = impl
}
