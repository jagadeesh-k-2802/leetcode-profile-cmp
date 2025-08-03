package com.jackappsdev.leetcode.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jackappsdev.leetcode.data.repository.UserRepositoryImpl
import com.jackappsdev.leetcode.domain.repository.UserRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.jackappsdev.leetcode")
class AppModule {

    @Single
    fun provideDataStore(): DataStore<Preferences> = DataStoreFactory().getDataStore()

    @Single
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl
}
