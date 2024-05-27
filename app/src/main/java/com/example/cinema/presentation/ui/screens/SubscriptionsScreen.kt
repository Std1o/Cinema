package com.example.cinema.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cinema.presentation.ui.components.LoadingIndicator
import com.example.cinema.presentation.ui.components.MoviesList
import com.example.cinema.presentation.viewmodel.SubscriptionsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph


@Destination<RootGraph>
@Composable
fun SubscriptionsScreen() {
    val viewModel = hiltViewModel<SubscriptionsViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    MoviesList(movies = uiState.movies) {}
    if (uiState.isLoading) LoadingIndicator()
}