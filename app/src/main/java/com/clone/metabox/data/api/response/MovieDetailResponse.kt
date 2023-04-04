package com.clone.metabox.data.api.response

import androidx.annotation.Keep
import java.time.LocalDate
import java.time.LocalDateTime

data class MovieDetailResponse (
    val movieId: String = "",
    val titleKr: String = "",
    val titleEn: String = "",
    val poster: String = "",
    val posterBg: String = "",
    val summaryTitle: String = "",
    val summaryDesc: String = "",
    val type: String = "",
    val genre: String = "",
    val grade: String = "",
    val openingDate: String = "",
    val runningTime: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val reviews: List<Reviews> = listOf(Reviews())
)

@Keep
data class Reviews(
    val movieId: String = "",
    val userId: String = "",
    val score: Int = 0,
    val viewPoint: List<String> = listOf(),
    val createdAt: String = ""
)
