package com.clone.metabox.view.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(

): ViewModel() {
    private val _movieListUiState: MutableStateFlow<MovieListUiState> =
        MutableStateFlow(MovieListUiState())

    val movieListUiState: StateFlow<MovieListUiState>
        get() = _movieListUiState

    init {
        _movieListUiState.value = _movieListUiState.value.copy(
            loadMoreMovieList = { loadMoreMovieList() }
        )
    }

    private fun loadMoreMovieList () = viewModelScope.launch {
        addOffset()

        _movieListUiState.value = _movieListUiState.value.copy(
            movieList = List(_movieListUiState.value.offset) { index -> "$index" }
        )

    }

    private suspend fun addOffset () {
        _movieListUiState.value = _movieListUiState.value.copy(
            offset = _movieListUiState.value.offset + 10,
        )
    }
}