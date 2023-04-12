package com.clone.metabox.view.movielist

import android.content.Context
import com.clone.metabox.data.api.response.MovieListResponse

data class MovieListUiState (
    val movieList: MovieListResponse = MovieListResponse(),
    val offset: Int = 0,
    val loadMoreMovieList: () -> Unit = {},
    val navigateToMovieDetail: (String) -> Unit = {},
//    val navigateMovieDetail: (String) -> Unit = { context, movieId -> }
)