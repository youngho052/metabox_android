package com.clone.metabox.data.api

import com.clone.metabox.data.api.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {
    @GET("api/v1/movie/{movieId}")
    suspend fun getMovieDetail (@Path("movieId") movieId: String): MovieDetailResponse
}