package com.example.warpspeedassessment.data.remote.dtos

data class MovieCastsResponseDto(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)