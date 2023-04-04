package com.clone.metabox.view.moviedetail

import com.clone.metabox.data.api.response.MovieDetailResponse

data class MovieDetailUiState (
    val sectionState: String = "information",
    val movieDetailInformation: MovieDetailResponse = MovieDetailResponse()
)
