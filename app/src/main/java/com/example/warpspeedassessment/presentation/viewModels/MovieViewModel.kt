package com.example.warpspeedassessment.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.warpspeedassessment.domain.models.MovieDetails
import com.example.warpspeedassessment.domain.usecases.contracts.GetMovieDetailsRepository
import com.example.warpspeedassessment.domain.usecases.contracts.SearchMovieRepository
import com.example.warpspeedassessment.presentation.viewStates.ViewState
import com.example.warpspeedassessment.utils.AppConstants.IO_DISPATCHER_DI_NAME
import com.example.warpspeedassessment.utils.AppConstants.SAMPLE_MOVIE_SEARCH_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieDetailsRepository: GetMovieDetailsRepository,
    private val searchMovieRepository: SearchMovieRepository,
    @Named(IO_DISPATCHER_DI_NAME) private val ioDispatcher: CoroutineContext
) : ViewModel() {
    private val _movieDetails: MutableLiveData<ViewState<MovieDetails>> =
        MutableLiveData(ViewState.initialDefault(null))
    val movieDetails: LiveData<ViewState<MovieDetails>> get() = _movieDetails

    private val searchQuery: MutableLiveData<String> = MutableLiveData(SAMPLE_MOVIE_SEARCH_QUERY)
    val movieSearchResult = searchQuery.switchMap {
        liveData(viewModelScope.coroutineContext + ioDispatcher) {
            emit(ViewState.loading(null))
            try {
                searchMovieRepository.searchMovie(it, 1).collect {
                    emit(ViewState.success(it))
                }
            } catch (e: Exception) {
                emit(ViewState.error(null))
            }
        }
    }

    fun getMovieDetails(movieId: String) {
        _movieDetails.value = ViewState.loading(null)
        viewModelScope.launch(ioDispatcher) {
            _movieDetails.postValue(getMovieDetailsRepository.getMovieDetails(movieId))
        }
    }

    fun searchMovie(query: String) {
        searchQuery.value = query
    }
}