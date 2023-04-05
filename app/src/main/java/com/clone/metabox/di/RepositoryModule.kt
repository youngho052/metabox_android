package com.clone.metabox.di

import com.clone.metabox.data.api.KaKaoLoginService
import com.clone.metabox.data.api.MainPageService
import com.clone.metabox.data.api.MovieDetailService
import com.clone.metabox.data.api.MovieListService
import com.clone.metabox.data.auth.DefaultKaKaoLoginRepository
import com.clone.metabox.data.auth.KakaoLoginRepository
import com.clone.metabox.data.main.DefaultMainPageRepository
import com.clone.metabox.data.main.MainPageRepository
import com.clone.metabox.data.movie.DefaultMovieDetailRepository
import com.clone.metabox.data.movie.DefaultMovieListRepository
import com.clone.metabox.data.movie.MovieDetailRepository
import com.clone.metabox.data.movie.MovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun mainPageRepository (
        service: MainPageService
    ): MainPageRepository {
        return DefaultMainPageRepository(
            service
        )
    }
    @Provides
    @Singleton
    fun movieListRepository (
        service: MovieListService
    ): MovieListRepository{
        return DefaultMovieListRepository (
            service
        )
    }
    @Provides
    @Singleton
    fun movieDetailRepository (
        service: MovieDetailService
    ): MovieDetailRepository {
        return DefaultMovieDetailRepository (
            service
        )
    }

    @Provides
    @Singleton
    fun kakaoLoginRepository (
        service: KaKaoLoginService
    ): KakaoLoginRepository {
        return DefaultKaKaoLoginRepository(
            service
        )
    }
}