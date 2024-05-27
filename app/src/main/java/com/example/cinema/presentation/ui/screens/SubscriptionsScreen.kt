package com.example.cinema.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cinema.common.iTems
import com.example.cinema.presentation.ui.components.MoviesList
import com.example.cinema.presentation.ui.components.SearchAppBar
import com.example.cinema.presentation.viewmodel.MoviesViewModel
import com.example.cinema.presentation.viewmodel.SubscriptionsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination<RootGraph>
@Composable
fun SubscriptionsScreen() {
    val viewModel = hiltViewModel<SubscriptionsViewModel>()
    val movies by viewModel.uiState.collectAsState()
    MoviesList(movies = movies) {}
}