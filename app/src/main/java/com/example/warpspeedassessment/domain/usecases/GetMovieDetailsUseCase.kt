package com.example.warpspeedassessment.domain.usecases

import com.example.warpspeedassessment.data.remote.apis.TMDBRequestsApiService
import com.example.warpspeedassessment.data.remote.networkUtils.NetworkUtils
import com.example.warpspeedassessment.domain.contracts.GetMovieDetailsRepository
import com.example.warpspeedassessment.domain.models.MovieDetails
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieDetails
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val tmdbRequestsApiService: TMDBRequestsApiService
) : GetMovieDetailsRepository {
    override suspend fun getMovieDetails(
        movieId: String,
        language: String
    ): ViewState<MovieDetails?> {
        val response = tmdbRequestsApiService.getMovieDetails(movieId, language)
        return networkUtils.getServerResponse(response).let {
            if (it.content != null) {
                val movie = it.content.toMovieDetails()
                val result = ViewState(it.status, movie, it.message)
                result
            } else {
                ViewState(it.status, null, it.message)
            }
        }
    }
}