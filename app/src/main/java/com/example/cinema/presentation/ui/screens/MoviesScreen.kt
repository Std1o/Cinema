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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cinema.R
import com.example.cinema.common.iTems
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
//                viewModel.searchPrefix = it
//                viewModel.getResults()
            }
        }
    ) { contentPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(contentPadding).padding(top = 30.dp)) {
            iTems(movies, key = { it }) { movie ->
                val shape = RoundedCornerShape(5.dp)
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    shape = shape,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            navigator.navigate(MovieScreenDestination(movie))
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                            .height(300.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        AsyncImage(
                            model = movie.poster.url,
                            contentDescription = "Translated description of what the image contains",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.FillWidth
                        )
                        Row {
                            Text(
                                text = movie.name,
                                modifier = Modifier.padding(top = 8.dp),
                                fontSize = 20.sp,
                                color = Color.White,
                                style = TextStyle(
                                    shadow = Shadow(
                                        Color.Black,
                                        Offset(3.0f, 4.95f),
                                        1.0f
                                    )
                                )
                            )
                            Text(
                                text = "• ${movie.year}",
                                modifier = Modifier.padding(top = 10.dp, start = 8.dp),
                                color = Color.Gray,
                            )
                        }
                        Text(
                            text = movie.genres.joinToString { it.name },
                            color = Color.Gray,
                        )
                        Text(
                            text = movie.shortDescription ?: movie.description,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}