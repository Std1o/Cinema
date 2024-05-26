package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.models.Movie
import com.example.cinema.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isUserAuthorized = MutableStateFlow(false)
    val isUserAuthorized = _isUserAuthorized.asStateFlow()

    fun checkUserAuthorized() {
        viewModelScope.launch {
            repository.isUserAuthorized().collect {
                _isUserAuthorized.value = it
            }
        }
    }

    fun setUserAuthorized(authorized: Boolean) {
        viewModelScope.launch {
            repository.setUserAuthorized(authorized)
        }
    }
}