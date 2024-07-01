package com.example.warpspeedassessment.di

import com.example.warpspeedassessment.data.remote.apis.TMDBRequestsApiService
import com.example.warpspeedassessment.data.remote.interceptors.AuthInterceptor
import com.example.warpspeedassessment.data.remote.networkUtils.NetworkUtils
import com.example.warpspeedassessment.utils.AppConstants.API_KEY_DI_NAME
import com.example.warpspeedassessment.utils.AppConstants.AUTH_INTERCEPTOR_DI_NAME
import com.example.warpspeedassessment.utils.AppConstants.BASE_URL_DI_NAME
import com.example.warpspeedassessment.utils.AppConstants.LOGGING_INTERCEPTOR_DI_NAME
import com.example.warpspeedassessment.utils.AppConstants.TIME_OUT_20
import com.example.warpspeedassessment.utils.AppParameters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named(BASE_URL_DI_NAME)
    fun providesAppBaseUrl(): String = AppParameters.BASE_URL

    @Provides
    @Singleton
    @Named(API_KEY_DI_NAME)
    fun providesApiKey(): String = AppParameters.API_KEY

    @Singleton
    @Provides
    @Named(LOGGING_INTERCEPTOR_DI_NAME)
    fun providesLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    @Named(AUTH_INTERCEPTOR_DI_NAME)
    fun providesInterceptor(authInterceptor: AuthInterceptor): Interceptor = authInterceptor

    @Singleton
    @Provides
    fun providesOKHTTPClient(
        @Named(LOGGING_INTERCEPTOR_DI_NAME) loggingInterceptor: Interceptor,
        @Named(AUTH_INTERCEPTOR_DI_NAME) authInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(TIME_OUT_20, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_20, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun providesRetrofitForAppNetworkCall(
        okHttpClient: OkHttpClient,
        @Named(BASE_URL_DI_NAME) baseUrl: String,
    ): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesTMDBRequestsApiService(
        retrofit: Retrofit
    ): TMDBRequestsApiService = retrofit.create(TMDBRequestsApiService::class.java)

    @Singleton
    @Provides
    fun providesNetworkUtils(): NetworkUtils = NetworkUtils()
}