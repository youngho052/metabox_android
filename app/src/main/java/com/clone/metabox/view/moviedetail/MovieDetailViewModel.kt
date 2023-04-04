package com.clone.metabox.view.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.domain.movie.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.clone.metabox.result.Result
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
):ViewModel() {
    private val _movieDetailUiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState())

    val movieDetailUiState: StateFlow<MovieDetailUiState>
        get() = _movieDetailUiState

    companion object {
        const val MOVIE_ID = "movieId"
    }

    private val movieId: String
        get() = savedStateHandle.get<String>(MOVIE_ID) ?: ""

    init {
        getMovieDetail()
    }

    private fun getMovieDetail () = viewModelScope.launch {
        if(movieId != "") {
            getMovieDetailUseCase(movieId).collectLatest {
                Timber.d("check movie detail $it")
                if(it is Result.Success) {
                    _movieDetailUiState.value = _movieDetailUiState.value.copy(
                        movieDetailInformation = it.data
                    )
                }
            }
        }
    }
}