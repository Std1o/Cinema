package com.example.cinema.presentation.ui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cinema.common.iTems
import com.example.cinema.domain.models.Movie

@Composable
fun MoviesList(movies: List<Movie>, modifier: Modifier = Modifier, onItemClick: (Movie) -> Unit) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        iTems(movies, key = { it }) { movie ->
            val shape = RoundedCornerShape(5.dp)
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                shape = shape,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(movie)
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
                        model = movie.poster.url ?: movie.poster.previewUrl,
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