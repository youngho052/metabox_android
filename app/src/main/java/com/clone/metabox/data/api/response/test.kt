package com.clone.metabox.data.api.response

import androidx.annotation.Keep

@Keep
data class GoodsListResponse (
    val brandName: String = "",
    val discountRate: Int = 0,
    val consumerPrice: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val thumbnailUrl: String = ""
)