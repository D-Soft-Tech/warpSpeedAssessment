package com.example.warpspeedassessment.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCastsAndCrew(
    val id: String,
    val cast: List<MovieCastDomain>,
    val director: MovieCrewDomain?
) : Parcelable