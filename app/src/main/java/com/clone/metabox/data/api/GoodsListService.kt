package com.clone.metabox.data.api

import com.clone.metabox.data.api.response.GoodsListResponse
import retrofit2.http.GET

interface GoodsListService {
    @GET("goodslist.json")
    suspend fun getGoodsList(): List<GoodsListResponse>
}