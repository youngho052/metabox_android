package com.clone.metabox.di

import android.content.Context
import com.clone.metabox.BuildConfig
import com.clone.metabox.data.api.KaKaoLoginService
import com.clone.metabox.data.api.MainPageService
import com.clone.metabox.data.api.MovieDetailService
import com.clone.metabox.data.api.MovieListService
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [ApiModule.Providers::class])
object ApiModule {
    @Provides
    @Singleton
    fun provideMainPageService(
        retrofit: Retrofit
    ): MainPageService {
        return retrofit.create(MainPageService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieListService(
        retrofit: Retrofit
    ): MovieListService {
        return retrofit.create(MovieListService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailService(
        retrofit: Retrofit
    ): MovieDetailService {
        return retrofit.create(MovieDetailService::class.java)
    }

    @Provides
    @Singleton
    fun provideKaKaoLoginService(
        retrofit: Retrofit
    ): KaKaoLoginService {
        return retrofit.create(KaKaoLoginService::class.java)
    }

    @InstallIn(SingletonComponent::class)
    @Module
    internal object Providers {
        @Provides
        @Singleton
        fun provideOkHttpClient(
            @ApplicationContext context: Context,
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                })
                .cache(Cache(context.cacheDir, 1 * 1024 * 1024)) // 1 MB
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_END_POINT)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
}