package com.example.warpspeedassessment.presentation.adapters.interactors

import androidx.recyclerview.widget.DiffUtil
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.presentation.adapters.pagingAdapter.GenericPagingRecyclerViewAdapter
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MoviePagingRecyclerViewAdapterFactory {
    fun createGenericPagingRecyclerViewAdapter(
        layoutId: Int,
        bindingInterface: GenericPagingRvViewBindingInterface<Movie>,
        comparator: DiffUtil.ItemCallback<Any>,
    ): GenericPagingRecyclerViewAdapter<Movie>
}