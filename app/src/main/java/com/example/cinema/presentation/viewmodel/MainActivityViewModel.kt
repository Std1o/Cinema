package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.repository.AuthRepository
import com.example.cinema.presentation.ui.models.MainActivityUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUIState())
    val uiState = _uiState.asStateFlow()

    init {
        checkUserAuthorized()
    }

    private fun checkUserAuthorized() {
        viewModelScope.launch {
            repository.isUserAuthorized().collect { isUserAuthorized ->
                _uiState.update { it.copy(isUserAuthorized = isUserAuthorized, isLoading = false) }
            }
        }
    }
}