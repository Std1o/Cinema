package com.example.cinema.di

import com.example.cinema.data.repository.AuthRepositoryImpl
import com.example.cinema.data.repository.MoviesRepositoryImpl
import com.example.cinema.data.repository.SubscriptionsRepositoryImpl
import com.example.cinema.domain.repository.AuthRepository
import com.example.cinema.domain.repository.MoviesRepository
import com.example.cinema.domain.repository.SubscriptionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMoviesRepository(repository: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindSubscriptionsRepository(repository: SubscriptionsRepositoryImpl): SubscriptionsRepository
}