package com.clone.metabox.domain.auth

import com.clone.metabox.data.auth.KakaoLoginRepository
import com.clone.metabox.di.IODispatcher
import com.clone.metabox.domain.FlowUseCase
import com.clone.metabox.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val kakaoLoginRepository: KakaoLoginRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<Unit, String>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<String>> = flow {
        emit(Result.Loading)
        emit(kakaoLoginRepository.kakaoLogin())
    }
}