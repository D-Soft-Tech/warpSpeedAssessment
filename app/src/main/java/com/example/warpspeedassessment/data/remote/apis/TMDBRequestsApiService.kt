package com.example.warpspeedassessment.data.remote.apis

import com.example.warpspeedassessment.data.remote.annotations.AuthorizedRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBRequestsApiService {
    @GET("search/movie?query={movie_title}&include_adult=false&language=en-US&page=1")
    @AuthorizedRequest
    fun searchMovie(
        @Query("query") query: String,
        @Query("include_adult") includesAdult: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int
    )

    @GET("/movie/{movie_id}")
    @AuthorizedRequest
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("language") language: String
    )

    @GET("movie_id/credits?language=en-US")
    @AuthorizedRequest
    fun getMovieCasts(
        @Path("movie_id") movieId: String,
        @Path("credits") credits: String,
        @Query("language") language: String
    )
}