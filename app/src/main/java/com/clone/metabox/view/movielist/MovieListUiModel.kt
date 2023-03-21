package com.clone.metabox.view.movielist

data class MovieListUiState (
    val movieList: List<String> = List(10) { index -> "$index" },
    val offset: Int = 10,
    val loadMoreMovieList: () -> Unit = {}
)