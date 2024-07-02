package com.example.warpspeedassessment.domain.usecases.contracts

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SearchMovieRepository {
    fun searchMovie(
        searchQuery: String,
        pageNumber: Int,
        includesAdult: Boolean = false,
        language: String = "en-US"
    ): Pager<Int, Movie>
}