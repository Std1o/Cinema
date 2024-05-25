package com.example.cinema.presentation.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.cinema.presentation.viewmodel.MoviesViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@Destination<RootGraph>(start = true) // sets this as a destination of the "root" nav graph
@Composable
fun MainScreen(navigator: DestinationsNavigator, modifier: Modifier) {
    val viewModel = hiltViewModel<MoviesViewModel>()
    val movies by viewModel.uiState.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        iTems(movies, key = { it }) { movie ->
            Box(
                modifier = Modifier
                    .animateItemPlacement()
                    .padding(vertical = 10.dp, horizontal = 16.dp)
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            ) {
                AsyncImage(
                    model = movie.poster.url,
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = movie.name,
                    modifier = Modifier.padding(16.dp),
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
            }
        }
    }
}