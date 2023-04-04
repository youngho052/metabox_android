package com.clone.metabox.data.api.response

import androidx.annotation.Keep

@Keep
data class MainPageResponse (
    val boxOffice: List<BoxOffice> = listOf(BoxOffice()) ,
    val recommandMovie: RecommendMovie = RecommendMovie(),
    val popUp: PopUp = PopUp()
)

@Keep
data class BoxOffice (
    val movieId: String = "",
    val poster: String = "",
    val summaryDesc: String = "",
    val titleKr: String = "",
)

@Keep
data class RecommendMovie (
    val _id: String = "",
    val titleKr: String = "",
    val posterBg: String = "",
)

@Keep
data class PopUp (
    val img: String =""
)