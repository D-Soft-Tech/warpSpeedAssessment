package com.example.warpspeedassessment.data.remote.dtos

data class MovieSearchResponseDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)