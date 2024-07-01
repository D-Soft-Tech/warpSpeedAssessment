package com.example.warpspeedassessment.presentation.adapters.assistedFactories

import com.example.warpspeedassessment.presentation.adapters.pagingAdapter.MoviePagingAdapter
import com.example.warpspeedassessment.presentation.adapters.pagingAdapter.MoviesClickListener
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MovieRecyclerViewPagingAdapterFactory {
    fun createMovieRecyclerViewPagingAdapter(
        onClickListener: MoviesClickListener
    ): MoviePagingAdapter
}