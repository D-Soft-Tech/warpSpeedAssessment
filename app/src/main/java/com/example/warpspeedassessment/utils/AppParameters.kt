package com.example.warpspeedassessment.utils

object AppParameters {
    init {
        System.loadLibrary("api-keys")
    }

    private external fun getBaseUrl(): String
    val BASE_URL = getBaseUrl()

    private external fun getApiKey(): String
    val API_KEY = getApiKey()

    private external fun getPosterBaseUrl(): String
}