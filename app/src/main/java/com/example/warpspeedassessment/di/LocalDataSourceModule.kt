package com.example.warpspeedassessment.di

import com.example.warpspeedassessment.data.local.sharedPrefs.SharedPrefManager
import com.example.warpspeedassessment.data.local.sharedPrefs.SharedPrefManagerContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(
        encryptedSharedPreferences: SharedPrefManager,
    ): SharedPrefManagerContract = encryptedSharedPreferences


}