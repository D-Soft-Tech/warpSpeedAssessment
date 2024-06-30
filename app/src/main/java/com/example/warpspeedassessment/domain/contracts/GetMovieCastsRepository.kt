package com.example.warpspeedassessment.domain.contracts

import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieCastsRepository {
    fun getMovieCasts(
        movieId: String,
        credits: String,
        language: String = "en-US"
    )
}