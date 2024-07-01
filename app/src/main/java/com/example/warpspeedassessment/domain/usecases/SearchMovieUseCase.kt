package com.example.warpspeedassessment.domain.usecases

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.warpspeedassessment.data.local.database.db.AppDatabase
import com.example.warpspeedassessment.data.remote.apis.TMDBRequestsApiService
import com.example.warpspeedassessment.data.remote.networkUtils.NetworkUtils
import com.example.warpspeedassessment.domain.usecases.contracts.SearchMovieRepository
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.domain.pagination.MovieRemoteMediator
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import com.example.warpspeedassessment.utils.AppConstants.PAGE_SIZE
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMovieUseCase @Inject constructor(
    private val appDatabase: AppDatabase,
    private val tmdbRequestsApiService: TMDBRequestsApiService
) : SearchMovieRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun searchMovie(
        searchQuery: String,
        pageNumber: Int,
        includesAdult: Boolean,
        language: String
    ): Flow<PagingData<Movie>> {
        val pagingSrcFactory = { appDatabase.getMovieDao().getMovies(searchQuery) }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = MovieRemoteMediator(appDatabase, tmdbRequestsApiService, searchQuery),
            pagingSourceFactory = pagingSrcFactory
        ).flow
    }
}