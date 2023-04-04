package com.clone.metabox.view.movielist

import android.content.Context
import android.content.Intent
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

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
): ViewModel() {
    private val _movieListUiState: MutableStateFlow<MovieListUiState> =
        MutableStateFlow(MovieListUiState())

    val movieListUiState: StateFlow<MovieListUiState>
        get() = _movieListUiState

    init {
        _movieListUiState.value = _movieListUiState.value.copy(
            loadMoreMovieList = { loadMoreMovieList() },
            navigateMovieDetail = { context, movieId -> navigateMovieDetail(context, movieId) }
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
        addOffset()

        getMovieListUseCase(_movieListUiState.value.offset).collectLatest {
            Timber.d("get movie list $it")

            if(it is Result.Success) {
                _movieListUiState.value = _movieListUiState.value.copy(
                    movieList = _movieListUiState.value.movieList.copy(
                        movies = _movieListUiState.value.movieList.movies.plus(it.data.movies)
                    )
                )
            }
        }
    }

    private fun addOffset () {
        _movieListUiState.value = _movieListUiState.value.copy(
            offset = _movieListUiState.value.offset + 20
        )
    }

    private fun navigateMovieDetail (context: Context, movieId: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)

        intent.putExtra("movieId", movieId)

        context.startActivity(intent)
    }
}