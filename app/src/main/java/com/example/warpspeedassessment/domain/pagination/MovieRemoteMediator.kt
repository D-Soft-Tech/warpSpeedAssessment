package com.example.warpspeedassessment.domain.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.warpspeedassessment.data.local.database.db.AppDatabase
import com.example.warpspeedassessment.data.remote.apis.TMDBRequestsApiService
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovie
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val database: AppDatabase,
    private val apiService: TMDBRequestsApiService,
    private val searchQuery: String
) : RemoteMediator<Int, Movie>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.pageNumber + 1
            }
        }

        try {
            val response = apiService.searchMovie(searchQuery, false, "en-US", page)
            val movies = response.body()!!.let { movieDto ->
                movieDto.results.map { it.toMovie(movieDto.page) }
            }
            val endOfPaginationReached = movies.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.getMovieDao().clearAll()
                }
                database.getMovieDao().insertMovies(movies.map { it.toMovieEntity() })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}