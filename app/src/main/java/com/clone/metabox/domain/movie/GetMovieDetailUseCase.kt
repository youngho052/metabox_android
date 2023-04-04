package com.clone.metabox.domain.movie

import com.clone.metabox.data.api.response.MovieDetailResponse
import com.clone.metabox.data.movie.MovieDetailRepository
import com.clone.metabox.di.IODispatcher
import com.clone.metabox.domain.FlowUseCase
import com.clone.metabox.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
): FlowUseCase<String, MovieDetailResponse>(ioDispatcher) {
    override fun execute(parameters: String): Flow<Result<MovieDetailResponse>> = flow {
        emit(Result.Loading)
        emit(Result.Success(movieDetailRepository.getMovieDetail(parameters)))
    }
}