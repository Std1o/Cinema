package com.example.cinema.data.api

import com.example.cinema.domain.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {
    @GET("movie?token=DW95MF9-94G4Z7G-GRPXR4P-VW7P6XG&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=20")
    fun getMovies(@Query("page") page: Int): MovieResponse
}