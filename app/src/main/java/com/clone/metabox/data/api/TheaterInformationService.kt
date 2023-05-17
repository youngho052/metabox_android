package com.clone.metabox.data.api

import com.clone.metabox.data.api.response.TheaterResponse
import retrofit2.http.GET

interface TheaterInformationService {
    @GET("theater")
    suspend fun getTheaterInformation (): List<TheaterResponse>
}