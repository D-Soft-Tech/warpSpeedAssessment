package com.example.warpspeedassessment.di

import com.example.warpspeedassessment.domain.usecases.GetMovieDetailsUseCase
import com.example.warpspeedassessment.domain.usecases.SearchMovieUseCase
import com.example.warpspeedassessment.domain.usecases.contracts.GetMovieDetailsRepository
import com.example.warpspeedassessment.domain.usecases.contracts.SearchMovieRepository
import com.example.warpspeedassessment.utils.AppConstants.IO_DISPATCHER_DI_NAME
import com.example.warpspeedassessment.utils.AppConstants.MAIN_THREAD_DISPATCHER_DI_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesGetMovieDetailsRepository(
        movieDetailsUseCase: GetMovieDetailsUseCase
    ): GetMovieDetailsRepository = movieDetailsUseCase

    @Singleton
    @Provides
    fun providesSearhMovieRepository(
        searchMovieRepository: SearchMovieUseCase
    ): SearchMovieRepository = searchMovieRepository

    @Singleton
    @Provides
    @Named(IO_DISPATCHER_DI_NAME)
    fun providesIODispatchers(): CoroutineContext = Dispatchers.IO

    @Singleton
    @Provides
    @Named(MAIN_THREAD_DISPATCHER_DI_NAME)
    fun providesMainThreadDispatchers(): CoroutineContext = Dispatchers.Main
}