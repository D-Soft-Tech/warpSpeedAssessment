package com.example.warpspeedassessment.presentation.adapters.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.warpspeedassessment.R
import com.example.warpspeedassessment.databinding.MovieListRvItemLayoutBinding
import com.example.warpspeedassessment.databinding.TopCastsRvItemLayoutBinding
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.domain.models.MovieCastDomain
import com.example.warpspeedassessment.presentation.adapters.MovieCastItemPagingRvBindingInterfaceImpl
import com.example.warpspeedassessment.presentation.adapters.MovieItemPagingRvBindingInterfaceImpl
import com.example.warpspeedassessment.presentation.adapters.interactors.MovieRecyclerViewBindingInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCastPagingAdapter @Inject constructor() :
    PagingDataAdapter<MovieCastDomain, MovieCastPagingAdapter.ViewHolder>(COMPARATOR) {

    inner class ViewHolder(val view: ViewDataBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(
            bindingInterface: MovieRecyclerViewBindingInterface,
        ) {
            bindingInterface.bindData(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = DataBindingUtil.inflate<TopCastsRvItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.top_casts_rv_item_layout,
            parent,
            false,
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bindingData: MovieCastItemPagingRvBindingInterfaceImpl? =
            getItem(position)?.let { MovieCastItemPagingRvBindingInterfaceImpl(it) }
        if (bindingData != null) {
            holder.bind(bindingData)
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<MovieCastDomain>() {
            override fun areItemsTheSame(
                oldItem: MovieCastDomain,
                newItem: MovieCastDomain
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieCastDomain,
                newItem: MovieCastDomain
            ): Boolean =
                oldItem == newItem
        }
    }
}