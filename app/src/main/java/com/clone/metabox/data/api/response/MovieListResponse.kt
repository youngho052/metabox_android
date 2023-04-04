package com.clone.metabox.data.api.response

import androidx.annotation.Keep

@Keep
data class MovieListResponse (
    val moviesCount: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val movies: List<Movies> = listOf(Movies())
)

@Keep
data class Movies (
    val genre: String = "",
    val grade: String = "",
    val movieId: String = "",
    val openingDate: String = "",
    val poster: String = "",
    val review: String = "",
    val runningTime: String = "",
    val summaryDesc: String = "",
    val summaryTitle: String = "",
    val titleEn: String = "",
    val titleKr: String = "",
    val type: String = ""
)
