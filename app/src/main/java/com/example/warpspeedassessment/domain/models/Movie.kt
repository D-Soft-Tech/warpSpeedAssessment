package com.example.warpspeedassessment.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backDropPath: String?,
    val releaseDate: String,
    val overview: String
)