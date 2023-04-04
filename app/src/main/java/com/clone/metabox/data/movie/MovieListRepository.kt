package com.clone.metabox.data.movie

import com.clone.metabox.data.api.MovieListService
import com.clone.metabox.data.api.response.MovieListResponse

interface MovieListRepository {
    suspend fun getMovieList (offset: Int): MovieListResponse
}

class DefaultMovieListRepository(private val movieListService: MovieListService): MovieListRepository {
    override suspend fun getMovieList(offset: Int): MovieListResponse {
        return kotlin.run {
            movieListService.getMovieList(offset)
        }
    }
}