package com.example.warpspeedassessment.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.warpspeedassessment.R
import com.example.warpspeedassessment.databinding.FragmentSearchMovieBinding
import com.example.warpspeedassessment.presentation.adapters.assistedFactories.MovieRecyclerViewPagingAdapterFactory
import com.example.warpspeedassessment.presentation.adapters.pagingAdapter.MoviePagingAdapter
import com.example.warpspeedassessment.presentation.viewModels.MovieViewModel
import com.example.warpspeedassessment.presentation.viewStates.Status
import dagger.assisted.AssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private lateinit var binding: FragmentSearchMovieBinding

    private lateinit var toolBar: Toolbar
    private lateinit var searchIcon: ImageView
    private lateinit var searchView: SearchView
    private lateinit var appBarTitle: TextView
    private lateinit var rv: RecyclerView

    private lateinit var moviePagingAdapter: MoviePagingAdapter
    @Inject
    lateinit var moviePagingAdapterFactory: MovieRecyclerViewPagingAdapterFactory

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        searchIcon.setOnClickListener {
            toggleSearchView()
        }

//        moviePagingAdapter = moviePagingAdapterFactory.createMovieRecyclerViewPagingAdapter {
//            val action = SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieDetailsFragment(it.id)
//            findNavController().navigate(action)
//        }

        moviePagingAdapter = MoviePagingAdapter {
            val action = SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieDetailsFragment(it.id)
            findNavController().navigate(action)
        }

        rv.adapter = moviePagingAdapter

        binding.apply {
            appViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun initViews() {
        with(binding) {
            searchIcon = searchIconImg
            toolBar = appToolBar
            appBarTitle = toolbarTitle
            searchView = setupSearchView()
            appToolBar.addView(searchView)
            rv = movieListRv
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.movieSearchResult.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                moviePagingAdapter.submitData(viewLifecycleOwner.lifecycle, it.content!!)
            }
        }
    }

    private fun setupSearchView(): SearchView = SearchView(requireContext()).apply {
        queryHint = getString(R.string.search_movie)
        isIconified = false
        visibility = View.GONE

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                appBarTitle.visibility = View.VISIBLE
                searchIcon.visibility = View.VISIBLE
                searchView.visibility = View.GONE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })

        setOnCloseListener {
            appBarTitle.visibility = View.VISIBLE
            searchIcon.visibility = View.VISIBLE
            searchView.visibility = View.GONE
            true
        }
    }

    private fun toggleSearchView() {
        if (searchView.visibility == View.GONE) {
            appBarTitle.visibility = View.GONE
            searchIcon.visibility = View.GONE
            searchView.visibility = View.VISIBLE
            searchView.requestFocus()
        } else {
            appBarTitle.visibility = View.VISIBLE
            searchIcon.visibility = View.VISIBLE
            searchView.visibility = View.GONE
        }
    }
}