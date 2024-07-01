package com.example.warpspeedassessment.domain.contracts

import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import retrofit2.Response

interface SearchMovieRepository {
    suspend fun searchMovie(
        searchQuery: String,
        pageNumber: Int,
        includesAdult: Boolean = false,
        language: String = "en-US"
    ): ViewState<List<Movie>?>
}