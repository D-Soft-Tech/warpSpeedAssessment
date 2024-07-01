package com.example.warpspeedassessment.domain.usecases

import com.example.warpspeedassessment.data.remote.apis.TMDBRequestsApiService
import com.example.warpspeedassessment.data.remote.networkUtils.NetworkUtils
import com.example.warpspeedassessment.domain.contracts.GetMovieCastsRepository
import com.example.warpspeedassessment.domain.models.MovieCastsAndCrew
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieCastAndCrew
import javax.inject.Inject

class GetMovieCastsAndCrewsUseCase @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val tmdbRequestsApiService: TMDBRequestsApiService
) : GetMovieCastsRepository {
    override suspend fun getMovieCasts(
        movieId: String,
        credits: String,
        language: String
    ): ViewState<MovieCastsAndCrew?> {
        val movieCastResponse = tmdbRequestsApiService.getMovieCasts(movieId, credits, language)
        return networkUtils.getServerResponse(movieCastResponse).let {
            if (it.content != null) {
                val movie = it.content.toMovieCastAndCrew()
                val result = ViewState(it.status, movie, it.message)
                result
            } else {
                ViewState(it.status, null, it.message)
            }
        }
    }
}