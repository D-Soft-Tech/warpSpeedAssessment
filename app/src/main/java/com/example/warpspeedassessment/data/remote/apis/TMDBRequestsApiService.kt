package com.example.warpspeedassessment.data.remote.apis

import com.example.warpspeedassessment.data.remote.annotations.AuthorizedRequest
import com.example.warpspeedassessment.data.remote.dtos.MovieCastsResponseDto
import com.example.warpspeedassessment.data.remote.dtos.MovieDetailsResponseDto
import com.example.warpspeedassessment.data.remote.dtos.MovieSearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBRequestsApiService {
    @GET("search/movie?query={movie_title}&include_adult=false&language=en-US&page=1")
    @AuthorizedRequest
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("include_adult") includesAdult: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieSearchResponseDto>

    @GET("/movie/{movie_id}")
    @AuthorizedRequest
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("language") language: String
    ): Response<MovieDetailsResponseDto>

    @GET("movie_id/credits?language=en-US")
    @AuthorizedRequest
    suspend fun getMovieCasts(
        @Path("movie_id") movieId: String,
        @Path("credits") credits: String,
        @Query("language") language: String
    ): Response<MovieCastsResponseDto>
}