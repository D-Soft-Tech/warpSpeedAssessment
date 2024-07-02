package com.example.warpspeedassessment.presentation.adapters.bindingAdapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.warpspeedassessment.R
import com.example.warpspeedassessment.utils.AppParameters.IMAGE_BASE_URL

@BindingAdapter("android:loadPoster")
fun ImageView.loadPoster(posterUrl: String?) {
    posterUrl?.let {
        load("$IMAGE_BASE_URL$posterUrl") {
            crossfade(true)
            build()
        }
    }
}

@BindingAdapter("android:loadPosterCircularImage")
fun ImageView.loadPosterCircularImage(posterUrl: String?) {
    posterUrl?.let {
        load("$IMAGE_BASE_URL$posterUrl") {
            crossfade(true)
            placeholder(R.drawable.animated_loader_drawable)
            transformations(CircleCropTransformation())
        }
    }
}