package com.clone.metabox.data.api.response

import androidx.annotation.Keep

@Keep
data class TheaterResponse (
    val state: String = "",
    val items: List<TheaterItems> = listOf(TheaterItems())
)
@Keep
data class TheaterItems (
    val theaterId: String = "",
    val name: String = ""
)

