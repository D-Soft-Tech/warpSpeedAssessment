package com.example.warpspeedassessment.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieGenreDomain(
    val id: Int,
    val name: String
) : Parcelable