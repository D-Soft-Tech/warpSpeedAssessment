package com.example.warpspeedassessment.presentation.adapters

import androidx.databinding.ViewDataBinding
import com.example.warpspeedassessment.databinding.MovieListRvItemLayoutBinding
import com.example.warpspeedassessment.databinding.TopCastsRvItemLayoutBinding
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.domain.models.MovieCastDomain
import com.example.warpspeedassessment.domain.models.MovieDetails
import com.example.warpspeedassessment.presentation.adapters.interactors.MovieRecyclerViewBindingInterface
import com.example.warpspeedassessment.presentation.adapters.pagingAdapter.MoviePagingAdapter

class MovieCastItemPagingRvBindingInterfaceImpl(private val item: MovieCastDomain) :
    MovieRecyclerViewBindingInterface {
    override fun bindData(view: ViewDataBinding) {
        (view as TopCastsRvItemLayoutBinding).apply {
            topCast = item
        }
    }
}