package com.example.warpspeedassessment.domain.usecases

import com.example.warpspeedassessment.data.remote.apis.TMDBRequestsApiService
import com.example.warpspeedassessment.data.remote.networkUtils.NetworkUtils
import com.example.warpspeedassessment.domain.contracts.SearchMovieRepository
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovie
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val tmdbRequestsApiService: TMDBRequestsApiService,
    private val networkUtils: NetworkUtils
) : SearchMovieRepository {
    override suspend fun searchMovie(
        searchQuery: String,
        pageNumber: Int,
        includesAdult: Boolean,
        language: String
    ): ViewState<List<Movie>?> {
        val response = tmdbRequestsApiService.searchMovie(
            searchQuery, includesAdult, language, pageNumber
        )
        return networkUtils.getServerResponse(response).let {
            if (it.content != null) {
                val movie = it.content.results.map { res -> res.toMovie() }
                val result = ViewState(it.status, movie, it.message)
                result
            } else {
                ViewState(it.status, null, it.message)
            }
        }
    }
}