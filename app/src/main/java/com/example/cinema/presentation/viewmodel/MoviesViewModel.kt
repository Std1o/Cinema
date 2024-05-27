package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.common.Constants.ADMIN_EMAIL
import com.example.cinema.domain.repository.AuthRepository
import com.example.cinema.domain.repository.MoviesRepository
import com.example.cinema.presentation.ui.models.MoviesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    init {
        getMovies()
        checkUserIsAdmin()
    }

    private val _uiState = MutableStateFlow(MoviesUIState())
    val uiState = _uiState.asStateFlow()

    private fun getMovies() {
        viewModelScope.launch {
            val movies = moviesRepository.getMovies(1, null).movies
            _uiState.update { it.copy(movies = movies, isLoading = false) }
        }
    }

    private fun checkUserIsAdmin() {
        viewModelScope.launch {
            authRepository.getEmail().collect { email ->
                _uiState.update { it.copy(isUserAdmin = email == ADMIN_EMAIL) }
            }
        }
    }

    fun searchMovie(query: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val movies = moviesRepository.searchMovie(query).movies
            _uiState.update { it.copy(movies = movies, isLoading = false) }
        }
    }
}