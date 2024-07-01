package com.example.warpspeedassessment.domain.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCrewDomain(
    val id: String,
    val creditId: String,
    val job: String,
    val name: String,
    val profilePicture: String
): Parcelable