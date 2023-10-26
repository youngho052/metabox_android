package com.clone.metabox.data.main

import com.clone.metabox.data.api.GoodsListService
import com.clone.metabox.data.api.response.GoodsListResponse

interface GoodsListRepository {
    suspend fun getGoodsList(): List<GoodsListResponse>
}

class DefaultGoodsListRepository (private val goodsListService: GoodsListService): GoodsListRepository {
    override suspend fun getGoodsList(): List<GoodsListResponse> {
        return kotlin.run {
            goodsListService.getGoodsList()
        }
    }
}