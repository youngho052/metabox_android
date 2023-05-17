package com.clone.metabox.data.theater

import com.clone.metabox.data.api.TheaterDetailService
import com.clone.metabox.data.api.response.TheaterDetailResponse

interface TheaterDetailRepository {
    suspend fun getTheaterDetail (theaterId: String): TheaterDetailResponse
}

class DefaultTheaterDetailRepository (private val theaterDetailService: TheaterDetailService): TheaterDetailRepository {
    override suspend fun getTheaterDetail(theaterId: String): TheaterDetailResponse {
        return kotlin.run {
            theaterDetailService.getTheaterDetails(theaterId = theaterId)
        }
    }
}