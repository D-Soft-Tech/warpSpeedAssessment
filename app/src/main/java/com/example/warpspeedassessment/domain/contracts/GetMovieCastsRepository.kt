package com.example.warpspeedassessment.domain.contracts

import com.example.warpspeedassessment.domain.models.MovieCastsAndCrew
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieCastsRepository {
    suspend fun getMovieCasts(
        movieId: String,
        credits: String = "credits",
        language: String = "en-US"
    ): ViewState<MovieCastsAndCrew?>
}