package com.clone.metabox.domain.movie

import com.clone.metabox.data.api.response.MovieListResponse
import com.clone.metabox.data.movie.MovieListRepository
import com.clone.metabox.di.IODispatcher
import com.clone.metabox.domain.FlowUseCase
import com.clone.metabox.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val movieListRepository: MovieListRepository,
    @IODispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<Int, MovieListResponse>(ioDispatcher) {
    override fun execute(parameters: Int): Flow<Result<MovieListResponse>> = flow {
        emit(Result.Loading)
        emit(Result.Success(movieListRepository.getMovieList(parameters)))
    }
}