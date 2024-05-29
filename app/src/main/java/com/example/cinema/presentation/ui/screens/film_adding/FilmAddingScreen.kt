package com.example.cinema.presentation.ui.screens.film_adding

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.example.cinema.presentation.ui.components.ExoPlayerLifecycleObserver
import com.example.cinema.presentation.ui.theme.Purple40
import com.example.cinema.presentation.viewmodel.FilmAddingViewModel
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
    val viewModel = hiltViewModel<FilmAddingViewModel>()
    val genres by viewModel.genres.collectAsState()
    var imageData by remember { mutableStateOf(Uri.EMPTY) }
    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            imageData = it
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 55.dp)
                    .verticalScroll(rememberScrollState()),
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
                Text("Жанр", fontSize = 12.sp)
                GenresDropDown(genres)

                if (imageData == Uri.EMPTY) {
                    Button(
                        modifier = Modifier.padding(contentPadding),
                        onClick = { imagePicker.launch("image/*") }) {
                        Text("Добавить превью")
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(imageData),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .height(200.dp)
                            .width(300.dp)
                    )
                }
            }

            OutlinedButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(200.dp)
                    .padding(bottom = 8.dp),
                border = BorderStroke(1.dp, Purple40),
                onClick = {
                    Toast.makeText(context, "Фильм успешно добавлен", Toast.LENGTH_SHORT).show()
                    navigator.popBackStack()
                }) {
                Text("Сохранить", color = Purple40)
            }
        }
    }

    ExoPlayerLifecycleObserver(exoPlayer = exoPlayer, lifecycle = lifecycleOwner.value.lifecycle)
}