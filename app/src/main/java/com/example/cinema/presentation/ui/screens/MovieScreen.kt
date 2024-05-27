package com.example.cinema.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.cinema.domain.models.Movie
import com.example.cinema.presentation.ui.components.ExoPlayerLifecycleObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem.fromUri
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>
@Composable
fun MovieScreen(movie: Movie) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.Black
    ) {
        val videoURL =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        val context = LocalContext.current
        val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

        val exoPlayer = ExoPlayer.Builder(context).build().apply {
            setMediaItem(fromUri(videoURL))
            playWhenReady = true
            prepare()
        }

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Box(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .height(200.dp)
            ) {
                AndroidView(factory = {
                    StyledPlayerView(context).apply {
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        player = exoPlayer
                    }
                })
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row {
                    Text(
                        text = movie.name,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.White,
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
                    fontSize = 13.sp
                )
                Text(
                    text = movie.description,
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        ExoPlayerLifecycleObserver(
            exoPlayer = exoPlayer,
            lifecycle = lifecycleOwner.value.lifecycle
        )
    }
}