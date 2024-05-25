package com.example.cinema.data.repository

import com.example.cinema.data.api.MoviesApi
import com.example.cinema.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val api: MoviesApi) : MoviesRepository {
    override suspend fun getMovies(page: Int) = api.getMovies(page)
}