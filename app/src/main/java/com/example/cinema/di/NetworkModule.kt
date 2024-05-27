package com.example.cinema.di

import com.example.cinema.data.AuthInterceptor
import com.example.cinema.data.api.MoviesApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.kinopoisk.dev/v1.4/"

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun getHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(AuthInterceptor())
            .addInterceptor(httpLoggingInterceptor)

    @Provides
    @Singleton
    fun provideRetrofit(httpBuilder: OkHttpClient.Builder): Retrofit {
        val builder = GsonBuilder().disableHtmlEscaping().create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(builder))
            .baseUrl(BASE_URL)
            .client(httpBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)
}