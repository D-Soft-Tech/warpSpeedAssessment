package com.example.warpspeedassessment.domain.contracts

import android.graphics.Bitmap
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import retrofit2.Response

interface GetMoviePosterRepository {
    suspend fun getMoviePoster(
        posterUrl: String
    ): Response<ViewState<Bitmap>>
}