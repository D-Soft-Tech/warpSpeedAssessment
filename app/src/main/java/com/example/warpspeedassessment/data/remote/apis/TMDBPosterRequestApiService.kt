package com.example.warpspeedassessment.data.remote.apis

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBPosterRequestApiService {
    @GET("https://image.tmdb.org/t/p/w200/{poster_url}")
    suspend fun getMoviePoster(
        @Path("poster_url") posterUrl: String
    ): Response<ResponseBody>
}