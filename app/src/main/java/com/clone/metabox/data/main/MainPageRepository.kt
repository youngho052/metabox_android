package com.clone.metabox.data.main

import com.clone.metabox.data.api.MainPageService
import com.clone.metabox.data.api.response.MainPageResponse

interface MainPageRepository {
    suspend fun getMainInformation() : MainPageResponse
}

class DefaultMainPageRepository (private val mainPageService: MainPageService) : MainPageRepository {
    override suspend fun getMainInformation(): MainPageResponse {
        return kotlin.runCatching {
            mainPageService.getMainInformation()
        }.getOrThrow()
    }
}