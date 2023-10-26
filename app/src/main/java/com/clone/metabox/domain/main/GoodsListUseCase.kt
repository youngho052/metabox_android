package com.clone.metabox.domain.main

import com.clone.metabox.data.api.response.GoodsListResponse
import com.clone.metabox.data.main.GoodsListRepository
import com.clone.metabox.di.IODispatcher
import com.clone.metabox.domain.FlowUseCase
import com.clone.metabox.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GoodsListUseCase @Inject constructor(
    private val goodsListRepository: GoodsListRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<Unit, List<GoodsListResponse>>(ioDispatcher){
    override fun execute(parameters: Unit): Flow<Result<List<GoodsListResponse>>> = flow {
        emit(Result.Loading)
        emit(Result.Success(goodsListRepository.getGoodsList()))
    }
}