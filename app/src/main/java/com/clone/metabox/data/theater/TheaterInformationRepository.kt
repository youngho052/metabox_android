package com.clone.metabox.data.theater

import com.clone.metabox.data.api.TheaterInformationService
import com.clone.metabox.data.api.response.TheaterResponse

interface TheaterInformationRepository {
    suspend fun getTheaterInformation (): List<TheaterResponse>
}

class DefaultTheaterInformationRepository (private val theaterInformationService: TheaterInformationService): TheaterInformationRepository {
    override suspend fun getTheaterInformation(): List<TheaterResponse> {
        return kotlin.run {
            theaterInformationService.getTheaterInformation()
        }
    }
}