package com.example.warpspeedassessment.domain.usecases.contracts

import com.example.warpspeedassessment.domain.models.MovieDetails
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import retrofit2.Response

interface GetMovieDetailsRepository {
    suspend fun getMovieDetails(
        movieId: String,
        language: String = "en-US"
    ): ViewState<MovieDetails>
}