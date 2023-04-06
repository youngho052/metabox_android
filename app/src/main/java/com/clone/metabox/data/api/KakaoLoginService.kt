package com.clone.metabox.data.api

import retrofit2.http.GET

interface KaKaoLoginService {
    @GET("auth/kakao/authorize")
    suspend fun kakaoLogin (): String
}

