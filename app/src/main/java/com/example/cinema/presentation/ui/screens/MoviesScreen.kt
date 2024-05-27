package com.example.cinema.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cinema.presentation.ui.components.LoadingIndicator
import com.example.cinema.presentation.ui.components.MoviesList
import com.example.cinema.presentation.ui.components.SearchAppBar
import com.example.cinema.presentation.viewmodel.MoviesViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.FilmAddingScreenDestination
import com.ramcosta.composedestinations.generated.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination<RootGraph>(start = true)
@Composable
fun MoviesScreen(navigator: DestinationsNavigator) {
    val viewModel = hiltViewModel<MoviesViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            SearchAppBar {
                viewModel.searchMovie(it)
            }
        },
        floatingActionButton = {
            if (uiState.isUserAdmin) {
                FloatingActionButton(
                    onClick = { navigator.navigate(FilmAddingScreenDestination()) },
                    shape = CircleShape,
                    modifier = Modifier.padding(bottom = 10.dp, end = 4.dp)
                ) {
                    Icon(Icons.Filled.Add, "")
                }
            }
        }
    ) { contentPadding ->
        MoviesList(
            movies = uiState.movies,
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding())
        ) {
            navigator.navigate(MovieScreenDestination(it))
        }
    }
    if (uiState.isLoading) LoadingIndicator()
}