package com.clone.metabox.data.api.response

import androidx.annotation.Keep

@Keep
data class TheaterResponse (
    val test: String = "",
)
@Keep
data class Theaters (
    val theaterMap: Map<String, List<TheaterInfo>>
)

@Keep
data class TheaterInfo(
    val theaterName: String = "",
    val theaterId: String = ""
)