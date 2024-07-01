package com.example.warpspeedassessment.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(
    val id: String,
    val title: String,
    val posterPath: String,
    val backDropPath: String,
    val overview: String,
    val releaseDate: String,
    val runTime: String,
    val voteCount: String,
    val voteAverageRating: String,
    var directorName: String? = null,
    var casts: List<MovieCastDomain>? = null,
    val genres: List<MovieGenreDomain>,
) : Parcelable