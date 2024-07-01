package com.example.warpspeedassessment.di

import android.content.Context
import com.example.warpspeedassessment.data.local.database.dao.MovieDao
import com.example.warpspeedassessment.data.local.database.dao.MovieDetailsDao
import com.example.warpspeedassessment.data.local.database.db.AppDatabase
import com.example.warpspeedassessment.data.local.sharedPrefs.SharedPrefManager
import com.example.warpspeedassessment.data.local.sharedPrefs.SharedPrefManagerContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun providesAppDb(
        @ApplicationContext context: Context,
    ): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun providesMovieDao(
        appDatabase: AppDatabase
    ): MovieDao = appDatabase.getMovieDao()

    @Provides
    @Singleton
    fun providesMovieDetailsDao(
        appDatabase: AppDatabase
    ): MovieDetailsDao = appDatabase.getMovieDetailsDao()
}