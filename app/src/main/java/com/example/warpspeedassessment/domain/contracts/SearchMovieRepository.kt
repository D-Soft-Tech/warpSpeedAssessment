package com.example.warpspeedassessment.domain.contracts

import retrofit2.http.Query

interface SearchMovieRepository {
    fun searchMovie(
        searchQuery: String,
        pageNumber: Int
    )
}