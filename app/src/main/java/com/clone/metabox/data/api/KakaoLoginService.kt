package com.clone.metabox.data.api

import retrofit2.http.GET

interface KaKaoLoginService {
    @GET("api/v1/auth/kakao/authorize")
    suspend fun kakaoLogin (): String
}