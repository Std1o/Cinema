package com.example.cinema.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cinema.domain.models.Subscription
import com.example.cinema.domain.repository.SubscriptionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FilmAddingViewModel @Inject constructor(private val repository: SubscriptionsRepository) :
    ViewModel() {

    private val _genres = MutableStateFlow<List<Subscription>>(emptyList())
    val genres = _genres.asStateFlow()

    init {
        _genres.value = repository.getAllSubscriptions()
    }
}