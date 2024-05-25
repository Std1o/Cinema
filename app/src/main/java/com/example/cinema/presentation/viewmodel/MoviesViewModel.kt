package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.models.Movie
import com.example.cinema.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    init {
        getMovies()
    }

    private val _uiState = MutableStateFlow<List<Movie>>(emptyList())
    val uiState = _uiState.asStateFlow()

    private fun getMovies() {
        viewModelScope.launch {
            val movies = repository.getMovies(1).movies
            println(_uiState)
            _uiState.value = movies
        }
    }
}