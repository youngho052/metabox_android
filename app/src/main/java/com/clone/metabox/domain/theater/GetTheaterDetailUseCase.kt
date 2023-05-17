package com.clone.metabox.domain.theater

import com.clone.metabox.data.api.response.TheaterDetailResponse
import com.clone.metabox.data.theater.TheaterDetailRepository
import com.clone.metabox.di.IODispatcher
import com.clone.metabox.domain.FlowUseCase
import com.clone.metabox.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTheaterDetailUseCase @Inject constructor(
    private val theaterDetailRepository: TheaterDetailRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
): FlowUseCase<String, TheaterDetailResponse>(ioDispatcher) {
    override fun execute(theaterId: String): Flow<Result<TheaterDetailResponse>> = flow {
        emit(Result.Loading)
        emit(Result.Success(theaterDetailRepository.getTheaterDetail(theaterId)))
    }
}