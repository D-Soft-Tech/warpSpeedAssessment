package com.example.warpspeedassessment.presentation.adapters

import androidx.databinding.ViewDataBinding
import com.example.warpspeedassessment.databinding.MovieListRvItemLayoutBinding
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.presentation.adapters.interactors.MovieRecyclerViewBindingInterface

class MovieItemPagingRvBindingInterfaceImpl(private val item: Movie) :
    MovieRecyclerViewBindingInterface {
    override fun bindData(view: ViewDataBinding) {
        (view as MovieListRvItemLayoutBinding).apply {
            movie = item
        }
    }
}