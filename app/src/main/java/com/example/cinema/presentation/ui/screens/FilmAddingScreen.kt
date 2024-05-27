package com.example.cinema.presentation.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.cinema.presentation.ui.components.ExoPlayerLifecycleObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.Log
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import kotlinx.coroutines.launch

@Destination<RootGraph>
@Composable
fun FilmAddingScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    var videoIsPicked by rememberSaveable { mutableStateOf(false) }
    val exoPlayer = ExoPlayer.Builder(context).build().apply {
        prepare()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { contentPadding ->
        val videoPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri?.let {
                    exoPlayer.setMediaItem(MediaItem.fromUri(it))
                    videoIsPicked = true
                } ?: scope.launch {
                    snackbarHostState.showSnackbar("Видео не выбрано")
                }
            }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!videoIsPicked) {
                Button(
                    modifier = Modifier.padding(contentPadding),
                    onClick = { videoPicker.launch("video/*") }) {
                    Text("Выбрать видео")
                }
            } else {
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
            }
        }
    }

    ExoPlayerLifecycleObserver(exoPlayer = exoPlayer, lifecycle = lifecycleOwner.value.lifecycle)
}