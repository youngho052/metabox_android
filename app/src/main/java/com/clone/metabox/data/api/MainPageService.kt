package com.clone.metabox.data.api

import com.clone.metabox.data.api.response.MainPageResponse
import retrofit2.http.GET

interface MainPageService {
    @GET("api/v1/main")
    suspend fun getMainInformation (): MainPageResponse
}