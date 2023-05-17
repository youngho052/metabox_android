package com.clone.metabox.data.api

import com.clone.metabox.data.api.response.TheaterDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TheaterDetailService {
    @GET("theater/{theaterId}")
    suspend fun getTheaterDetails (@Path ("theaterId") theaterId: String): TheaterDetailResponse
}