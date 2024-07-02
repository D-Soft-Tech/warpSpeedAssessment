package com.example.warpspeedassessment.domain.usecases

import androidx.room.withTransaction
import com.example.warpspeedassessment.data.local.database.db.AppDatabase
import com.example.warpspeedassessment.data.remote.apis.TMDBRequestsApiService
import com.example.warpspeedassessment.data.remote.networkUtils.NetworkUtils
import com.example.warpspeedassessment.domain.models.MovieDetails
import com.example.warpspeedassessment.domain.usecases.contracts.GetMovieDetailsRepository
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieCastAndCrew
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieDetails
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieDetailsEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieDetailsUseCase @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val tmdbRequestsApiService: TMDBRequestsApiService,
    private val appDatabase: AppDatabase
) : GetMovieDetailsRepository {
    override suspend fun getMovieDetails(
        movieId: String,
        language: String
    ): ViewState<MovieDetails> {
        val response = tmdbRequestsApiService.getMovieDetails(movieId, language)
        val movieCastsResponse = tmdbRequestsApiService.getMovieCasts(movieId, "credits", "en-US")
        val movieDetailsFromDB = appDatabase.getMovieDetailsDao().getMovieDetails(movieId)
        return if (movieDetailsFromDB.isNotEmpty()) {
            ViewState.success(movieDetailsFromDB.first())
        } else {
            networkUtils.getServerResponse(response).let {
                if (it.content != null) {
                    val movieDetails = it.content.toMovieDetails()
                    if (movieCastsResponse.isSuccessful) {
                        val movieCastAndCrew = movieCastsResponse.body()!!.toMovieCastAndCrew()
                        movieDetails.apply {
                            casts = movieCastAndCrew.cast
                            directorName = movieCastAndCrew.director?.name
                        }
                        appDatabase.withTransaction {
                            appDatabase.getMovieDetailsDao().insertMovieDetails(movieDetails.toMovieDetailsEntity())
                        }
                        val result = ViewState(it.status, movieDetails, it.message)
                        result
                    } else {
                        ViewState(it.status, null, it.message)
                    }
                } else {
                    ViewState(it.status, null, it.message)
                }
            }
        }
    }
}