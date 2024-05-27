package com.example.cinema.domain.repository

import com.example.cinema.domain.models.MovieResponse

interface MoviesRepository {
    suspend fun getMovies(page: Int, genre: String?): MovieResponse
    suspend fun searchMovie(query: String): MovieResponse
}