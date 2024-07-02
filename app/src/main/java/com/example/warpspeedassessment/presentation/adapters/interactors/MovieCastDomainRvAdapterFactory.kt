package com.example.warpspeedassessment.presentation.adapters.interactors

import com.example.warpspeedassessment.domain.models.MovieCastDomain
import com.example.warpspeedassessment.presentation.adapters.genericRvAdapter.GenericRecyclerViewAdapter
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MovieCastDomainRvAdapterFactory {
    fun createOrderItemRvAdapter(
        layoutId: Int,
        bindingInterface: GenericRecyclerViewBindingInterface<MovieCastDomain>,
    ): GenericRecyclerViewAdapter<MovieCastDomain>
}