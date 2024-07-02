package com.example.warpspeedassessment.presentation.adapters.genericRvAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.warpspeedassessment.presentation.adapters.interactors.GenericRecyclerViewBindingInterface
import com.example.warpspeedassessment.presentation.adapters.diffUtils.GenericRecyclerViewDiffUtil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class GenericRecyclerViewAdapter<T> @AssistedInject constructor(
    @Assisted @LayoutRes
    private val layoutId: Int,
    @Assisted private val bindingInterface: GenericRecyclerViewBindingInterface<T>,
) :
    RecyclerView.Adapter<GenericRecyclerViewAdapter.ViewHolder<ViewDataBinding>>() {

    private var dataSet: List<T> = listOf()

    class ViewHolder<BINDING : ViewDataBinding>(val view: ViewDataBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun <T> bind(
            item: T,
            bindingInterface: GenericRecyclerViewBindingInterface<T>,
        ) {
            bindingInterface.bindData(item, view)
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

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder<ViewDataBinding>, position: Int) {
        holder.apply {
            bind(dataSet[position], bindingInterface)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<T>) {
        val diff = GenericRecyclerViewDiffUtil(newData, dataSet)
        val diffResult = DiffUtil.calculateDiff(diff)
        dataSet = newData
        notifyDataSetChanged()
        diffResult.dispatchUpdatesTo(this)
    }
}