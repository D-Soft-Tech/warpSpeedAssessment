package com.example.warpspeedassessment.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val posterPath: String?,
    val backDropPath: String?,
    val releaseDate: String,
    val overview: String,
    val voteCount: String,
    val voteAverageRating: String,
    val pageNumber: Int
): Parcelable