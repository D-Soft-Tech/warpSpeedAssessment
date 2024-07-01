package com.example.warpspeedassessment.domain.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCastDomain(
    val id: String,
    val castId: String,
    val name: String,
    val profilePicture: String,
) : Parcelable