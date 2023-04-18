package com.clone.metabox.view.movielist

import android.content.Context
import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.MovieDetailActivity
import com.clone.metabox.domain.movie.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.clone.metabox.result.Result
import com.clone.metabox.util.NavigatePages

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val navigatePages: NavigatePages
): ViewModel() {
    private val _movieListUiState: MutableStateFlow<MovieListUiState> =
        MutableStateFlow(MovieListUiState())

    val movieListUiState: StateFlow<MovieListUiState>
        get() = _movieListUiState

    companion object {
        const val MOVIE_NAV_STATE = "movieNavToState"
    }

    val movieNavToState: String
        get() = savedStateHandle.get<String>(MOVIE_NAV_STATE) ?: ""

    init {
        _movieListUiState.value = _movieListUiState.value.copy(
            loadMoreMovieList = { loadMoreMovieList() },
            navigateToMovieDetail = { movieId -> navigatePages.navigateMovieDetail(movieId) },
            navigateToTheaterSelector = { movieId -> navigatePages.navigateMultiTheaterSelector(movieId, "movie") }
        )

        getMovieList()
    }

    private fun getMovieList () = viewModelScope.launch {
        getMovieListUseCase(_movieListUiState.value.offset).collectLatest {
            if(it is Result.Success) {
                _movieListUiState.value = _movieListUiState.value.copy(
                    movieList = it.data
                )
            }
        }
    }

    private fun loadMoreMovieList () = viewModelScope.launch {
        _movieListUiState.value = _movieListUiState.value.copy(
            offset = _movieListUiState.value.offset + 20
        )

        getMovieListUseCase(_movieListUiState.value.offset).collectLatest {
            if(it is Result.Success) {
                _movieListUiState.value = _movieListUiState.value.copy(
                    movieList = _movieListUiState.value.movieList.copy(
                        movies = _movieListUiState.value.movieList.movies.plus(it.data.movies)
                    )
                )
            }
        }
    }
}