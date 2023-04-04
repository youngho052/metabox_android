package com.clone.metabox.data.movie

import com.clone.metabox.data.api.MovieDetailService
import com.clone.metabox.data.api.response.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: String): MovieDetailResponse
}

class DefaultMovieDetailRepository (private val movieDetailService: MovieDetailService): MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: String): MovieDetailResponse {
        return kotlin.run {
            movieDetailService.getMovieDetail(movieId)
        }
    }
}