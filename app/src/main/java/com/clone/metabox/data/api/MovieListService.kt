package com.clone.metabox.data.api

import com.clone.metabox.data.api.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {
    @GET("api/v1/movie")
    suspend fun getMovieList (
        @Query ("offset") offset: Int = 0
    ): MovieListResponse
}