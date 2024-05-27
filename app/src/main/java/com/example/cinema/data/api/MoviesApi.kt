package com.example.cinema.data.api

import com.example.cinema.domain.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {
    @GET("movie?field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=20")
    suspend fun getMovies(@Query("page") page: Int): MovieResponse

    @GET("movie/search")
    suspend fun searchMovie(@Query("query") query: String): MovieResponse
}