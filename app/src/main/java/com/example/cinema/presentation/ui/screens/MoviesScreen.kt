package com.example.cinema.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cinema.presentation.ui.components.MoviesList
import com.example.cinema.presentation.ui.components.SearchAppBar
import com.example.cinema.presentation.viewmodel.MoviesViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination<RootGraph>(start = true)
@Composable
fun MoviesScreen(navigator: DestinationsNavigator) {
    val viewModel = hiltViewModel<MoviesViewModel>()
    val movies by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            SearchAppBar {
                viewModel.searchMovie(it)
            }
        }
    ) { contentPadding ->
        MoviesList(
            movies = movies,
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding())
        ) {
            navigator.navigate(MovieScreenDestination(it))
        }
    }
}