package com.example.warpspeedassessment.presentation.adapters.interactors

import androidx.databinding.ViewDataBinding

interface GenericPagingRvViewBindingInterface<T> {
    fun bindData(view: ViewDataBinding)
    fun getAction(item: Any, view: ViewDataBinding)
    fun setItem(item: Any)
}