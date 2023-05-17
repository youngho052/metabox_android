package com.clone.metabox.domain.theater

import com.clone.metabox.data.api.response.TheaterResponse
import com.clone.metabox.data.theater.TheaterInformationRepository
import com.clone.metabox.di.IODispatcher
import com.clone.metabox.domain.FlowUseCase
import com.clone.metabox.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTheaterInformationUseCase @Inject constructor(
    private val theaterInformationRepository: TheaterInformationRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<Unit, List<TheaterResponse>>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<List<TheaterResponse>>> = flow {
        emit(Result.Loading)
        emit(Result.Success(theaterInformationRepository.getTheaterInformation()))
    }
}