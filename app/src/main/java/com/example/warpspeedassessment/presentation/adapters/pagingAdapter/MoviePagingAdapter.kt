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
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.presentation.adapters.MovieItemPagingRvBindingInterfaceImpl
import com.example.warpspeedassessment.presentation.adapters.interactors.MovieRecyclerViewBindingInterface
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

typealias MoviesClickListener = (transaction: Movie) -> Unit

class MoviePagingAdapter @AssistedInject constructor(
    @Assisted private val clickListener: MoviesClickListener
) :
    PagingDataAdapter<Movie, MoviePagingAdapter.ViewHolder>(COMPARATOR) {

    inner class ViewHolder(val view: ViewDataBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(
            bindingInterface: MovieRecyclerViewBindingInterface,
        ) {
            bindingInterface.bindData(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = DataBindingUtil.inflate<MovieListRvItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_list_rv_item_layout,
            parent,
            false,
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bindingData: MovieItemPagingRvBindingInterfaceImpl? =
            getItem(position)?.let { MovieItemPagingRvBindingInterfaceImpl(it) }
        if (bindingData != null) {
            holder.apply {
                bind(bindingData)
                itemView.setOnClickListener {
                    getItem(position)?.let { it1 ->
                        clickListener.invoke(it1)
                    }
                }
            }
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