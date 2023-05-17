package com.clone.metabox.view.movielist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.domain.movie.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.clone.metabox.result.Result
import com.clone.metabox.util.RouteNavigation
import timber.log.Timber

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val routeNavigation: RouteNavigation
): ViewModel() {
    private val _movieListUiState: MutableStateFlow<MovieListUiState> =
        MutableStateFlow(MovieListUiState())

    val movieListUiState: StateFlow<MovieListUiState>
        get() = _movieListUiState

    companion object {
        const val MOVIE_NAV_STATE = "movieNavToState"
    }

    private val movieListOffset: MutableState<Int> =
        mutableStateOf(0)

    val movieNavToState: String
        get() = savedStateHandle.get<String>(MOVIE_NAV_STATE) ?: ""

    init {
        _movieListUiState.value = _movieListUiState.value.copy(
            loadMoreMovieList = { loadMoreMovieList() },
            navigateToMovieDetail = { movieId -> routeNavigation.navigateMovieDetail(movieId) },
            navigateToTheaterSelector = { movieId -> routeNavigation.navigateMultiTheaterSelector(movieId, "movie") }
        )

        getMovieList()
    }

    private fun getMovieList () = viewModelScope.launch {
        getMovieListUseCase(movieListOffset.value).collectLatest {
            if(it is Result.Success) {
                _movieListUiState.value = _movieListUiState.value.copy(
                    movieList = it.data
                )
            }
        }
    }

    private fun loadMoreMovieList () = viewModelScope.launch {
        movieListOffset.value = movieListOffset.value + 24

        if(_movieListUiState.value.movieList.movies.isNotEmpty() &&
            (_movieListUiState.value.movieList.moviesCount > _movieListUiState.value.movieList.movies.size)
        ) {
            getMovieListUseCase(movieListOffset.value).collectLatest {
                if(it is Result.Success) {
                    _movieListUiState.value = _movieListUiState.value.copy(
                        movieList = _movieListUiState.value.movieList.copy(
                            offset = it.data.offset,
                            movies = _movieListUiState.value.movieList.movies.plus(it.data.movies)
                        )
                    )
                }
            }
        }
    }
}