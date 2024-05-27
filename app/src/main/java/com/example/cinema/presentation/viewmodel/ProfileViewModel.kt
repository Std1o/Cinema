package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.repository.AuthRepository
import com.example.cinema.presentation.ui.models.ProfileUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    init {
        initUIState()
    }

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState = _uiState.asStateFlow()

    private fun initUIState() {
        viewModelScope.launch {
            repository.getEmail().collect { email ->
                _uiState.update {
                    it.copy(email = email, id = UUID.randomUUID().toString().take(20))
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.setEmail("")
            repository.setUserAuthorized(false)
        }
    }
}