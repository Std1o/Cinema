package com.example.cinema.presentation.ui.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.cinema.presentation.ui.components.ExoPlayerLifecycleObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination<RootGraph>
@Composable
fun FilmAddingScreen(navigator: DestinationsNavigator) {
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

            val name = remember { mutableStateOf(TextFieldValue()) }
            val description = remember { mutableStateOf(TextFieldValue()) }

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Название фильма") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                value = name.value,
                onValueChange = { name.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Описание фильма") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                value = description.value,
                onValueChange = { description.value = it })

            Button(
                modifier = Modifier.padding(vertical = 16.dp),
                onClick = {
                    Toast.makeText(context, "Фильм успешно добавлен", Toast.LENGTH_SHORT).show()
                    navigator.popBackStack()
                }) {
                Text("Добавить")
            }
        }
    }

    ExoPlayerLifecycleObserver(exoPlayer = exoPlayer, lifecycle = lifecycleOwner.value.lifecycle)
}