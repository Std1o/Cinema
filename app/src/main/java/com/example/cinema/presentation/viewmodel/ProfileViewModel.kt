package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.models.Subscription
import com.example.cinema.domain.repository.AuthRepository
import com.example.cinema.domain.repository.SubscriptionsRepository
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
    private val authRepository: AuthRepository,
    private val subscriptionsRepository: SubscriptionsRepository,
) : ViewModel() {

    init {
        initUIState()
    }

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState = _uiState.asStateFlow()

    private fun initUIState() {
        viewModelScope.launch {
            authRepository.getEmail().collect { email ->
                _uiState.update {
                    it.copy(
                        email = email,
                        id = UUID.randomUUID().toString().take(20),
                        subscriptions = subscriptionsRepository.getSubscriptions(),
                        isLoading = false
                    )
                }
            }
        }
    }

    fun getAllSubscriptions() {
        _uiState.update { it.copy(allSubscriptions = subscriptionsRepository.getAllSubscriptions()) }
    }

    fun onSubscriptionAdded(subscription: Subscription) {
        _uiState.update { it.copy(subscriptions = it.subscriptions + subscription) }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.setEmail("")
            authRepository.setUserAuthorized(false)
        }
    }
}