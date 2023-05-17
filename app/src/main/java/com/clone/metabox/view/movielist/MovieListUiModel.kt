package com.clone.metabox.view.movielist

import androidx.annotation.Keep
import com.clone.metabox.data.api.response.MovieListResponse
@Keep
data class MovieListUiState (
    val movieList: MovieListResponse = MovieListResponse(),
    val loadMoreMovieList: () -> Unit = {},
    val navigateToMovieDetail: (String) -> Unit = {},
    val navigateToTheaterSelector: (String) -> Unit = {}
)