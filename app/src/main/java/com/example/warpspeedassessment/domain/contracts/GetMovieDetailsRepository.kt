package com.example.warpspeedassessment.domain.contracts

import com.example.warpspeedassessment.presentation.viewStates.ViewState
import retrofit2.Response
import retrofit2.http.Query

interface GetMovieDetailsRepository {
    suspend fun getMovieDetails(
        movieId: String,
        language: String = "en-US"
    ): Response<ViewState<>>
}