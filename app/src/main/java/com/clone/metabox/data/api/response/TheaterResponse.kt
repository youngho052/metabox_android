package com.clone.metabox.data.api.response

import androidx.annotation.Keep

@Keep
data class TheaterResponse (
    val state: String = "",
    val items: List<Items> = listOf(Items())
)
@Keep
data class Items (
    val theaterId: String = "",
    val name: String = ""
)

