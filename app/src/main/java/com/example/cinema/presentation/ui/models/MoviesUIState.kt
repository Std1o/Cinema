package com.example.cinema.presentation.ui.models

import com.example.cinema.domain.models.Movie

data class MoviesUIState(val movies: List<Movie> = emptyList(), val isLoading: Boolean = true)
