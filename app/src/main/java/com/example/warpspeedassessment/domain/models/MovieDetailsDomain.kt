package com.example.warpspeedassessment.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailsDomain(
    val movieDetails: MovieDetails,
    val movieCastsAndCrew: MovieCastsAndCrew,
) : Parcelable