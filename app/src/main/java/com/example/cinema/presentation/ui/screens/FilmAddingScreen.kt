package com.example.cinema.presentation.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream

@Destination<RootGraph>
@Composable
fun FilmAddingScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { contentPadding ->
        val videoPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri?.let {
                    val videoFile = createTmpFileFromUri(context = context, uri, "video")
                } ?: scope.launch {
                    snackbarHostState.showSnackbar("Видео не выбрано")
                }
            }
        )

        Button(
            modifier = Modifier.padding(contentPadding),
            onClick = { videoPicker.launch("video/*") }) {
            Text("Выбрать видео")
        }
    }
}

fun createTmpFileFromUri(context: Context, uri: Uri, fileName: String): File? {
    return try {
        val stream = context.contentResolver.openInputStream(uri)
        val file = File.createTempFile(fileName, ".mp4", context.cacheDir)
        file.copyInputStreamToFile(stream)
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun File.copyInputStreamToFile(inputStream: InputStream?) {
    outputStream().use { fileOut ->
        inputStream?.copyTo(fileOut)
    }
}