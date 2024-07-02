package com.example.warpspeedassessment.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.warpspeedassessment.R
import com.example.warpspeedassessment.databinding.FragmentMovieDetailsBinding
import com.example.warpspeedassessment.databinding.TopCastsRvItemLayoutBinding
import com.example.warpspeedassessment.domain.models.MovieCastDomain
import com.example.warpspeedassessment.presentation.adapters.genericRvAdapter.GenericRecyclerViewAdapter
import com.example.warpspeedassessment.presentation.adapters.interactors.GenericRecyclerViewBindingInterface
import com.example.warpspeedassessment.presentation.adapters.interactors.MovieCastDomainRvAdapterFactory
import com.example.warpspeedassessment.presentation.viewModels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var backIcon: ImageView
    private lateinit var progBar: ProgressBar
    private lateinit var rvAdapter: GenericRecyclerViewAdapter<MovieCastDomain>
    private lateinit var genericRvBindingInterface: GenericRecyclerViewBindingInterface<MovieCastDomain>
    private lateinit var errorTxtView: TextView
    private lateinit var rv: RecyclerView
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by activityViewModels()

    @Inject lateinit var movieCastAdapterFactory: MovieCastDomainRvAdapterFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)

        genericRvBindingInterface =
            object : GenericRecyclerViewBindingInterface<MovieCastDomain> {
                override fun bindData(item: MovieCastDomain, view: ViewDataBinding) {
                    (view as TopCastsRvItemLayoutBinding).apply {
                        topCast = item
                    }
                }
            }
        binding.apply {
            movieViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        rvAdapter = movieCastAdapterFactory.createOrderItemRvAdapter(
            R.layout.top_casts_rv_item_layout,
            genericRvBindingInterface,
        )
        rv.apply {
            adapter = rvAdapter
        }
        viewModel.getMovieDetails(args.selectedMovie.id)
        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            it.content?.casts?.let { movieCasts->
                rvAdapter.updateData(movieCasts)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        with(binding) {
            rv = movieCastsRv
            backIcon = imageView2
            errorTxtView = errorTextView
            progBar = progressBar2
        }
    }
}