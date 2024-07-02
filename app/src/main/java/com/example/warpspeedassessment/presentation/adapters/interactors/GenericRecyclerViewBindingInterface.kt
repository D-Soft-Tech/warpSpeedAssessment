package com.example.warpspeedassessment.presentation.adapters.interactors

import androidx.databinding.ViewDataBinding

interface GenericRecyclerViewBindingInterface<T> {
    fun bindData(item: T, view: ViewDataBinding)
}