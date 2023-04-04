package com.clone.metabox.domain.main

import com.clone.metabox.data.api.response.MainPageResponse
import com.clone.metabox.data.main.MainPageRepository
import com.clone.metabox.di.IODispatcher
import com.clone.metabox.domain.FlowUseCase
import com.clone.metabox.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMainPageUseCase @Inject constructor(
    private val mainPageRepository: MainPageRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<Unit, MainPageResponse>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<MainPageResponse>> = flow {
        emit(Result.Loading)
        emit(Result.Success(mainPageRepository.getMainInformation()))
    }
}