package com.clone.metabox.data.auth

import com.clone.metabox.data.api.KaKaoLoginService
import com.clone.metabox.result.Result

interface KakaoLoginRepository {
    suspend fun kakaoLogin (): Result.Success<String>
}

class DefaultKaKaoLoginRepository (private val kaKaoLoginService: KaKaoLoginService): KakaoLoginRepository {
    override suspend fun kakaoLogin(): Result.Success<String> {
        return kotlin.run {
            Result.Success(kaKaoLoginService.kakaoLogin())
        }
    }
}