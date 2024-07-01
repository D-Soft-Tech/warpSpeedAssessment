package com.example.warpspeedassessment.presentation.adapters.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.presentation.adapters.interactors.GenericPagingRvViewBindingInterface
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class GenericPagingRecyclerViewAdapter<T> @AssistedInject constructor(
    @Assisted @LayoutRes
    private val layoutId: Int,
    @Assisted private val bindingInterface: GenericPagingRvViewBindingInterface<T>,
    @Assisted private val comparator: DiffUtil.ItemCallback<Any>,
) :
    PagingDataAdapter<Any, GenericPagingRecyclerViewAdapter<T>.ViewHolder<ViewDataBinding>>(
        comparator
    ) {

    inner class ViewHolder<BINDING : ViewDataBinding>(val view: ViewDataBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun <T> bind(
            bindingInterface: GenericPagingRvViewBindingInterface<T>,
        ) {
            bindingInterface.bindData(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ViewDataBinding> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<ViewDataBinding>, position: Int) {
        val bindingData: GenericPagingRvViewBindingInterface<T>? =
            getItem(position)?.let {
                bindingInterface.setItem(it)
                bindingInterface
            }
        if (bindingData != null) {
            holder.bind(bindingData)
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean =
                oldItem == newItem
        }
    }
}