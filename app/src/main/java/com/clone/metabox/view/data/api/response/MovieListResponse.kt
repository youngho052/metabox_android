package com.clone.metabox.view.data.api.response

import androidx.annotation.Keep

@Keep
data class MovieListResponse (
    val id: String = "1",
    val thumbnail: String = "",
    val description: String = "",
    val score: Float = 0f,
    val like: Int = 0,
    val title: String = "",
    val reservationRate: Float = 0f,
)

