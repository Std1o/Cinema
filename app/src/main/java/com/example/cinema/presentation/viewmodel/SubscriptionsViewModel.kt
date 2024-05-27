package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.models.Movie
import com.example.cinema.domain.repository.MoviesRepository
import com.example.cinema.presentation.ui.models.MoviesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionsViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    init {
        getMovies()
    }

    private val _uiState = MutableStateFlow(MoviesUIState())
    val uiState = _uiState.asStateFlow()

    private fun getMovies() {
        viewModelScope.launch {
            val movies = repository.getMovies(1, "фантастика").movies
            _uiState.update { it.copy(movies = movies, isLoading = false) }
        }
    }
}