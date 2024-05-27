package com.example.cinema.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.util.Log

@Composable
fun ExoPlayerLifecycleObserver(exoPlayer: ExoPlayer, lifecycle: Lifecycle) {
    DisposableEffect(key1 = Unit, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    Log.e("LIFECYCLE", "resumed")
                    exoPlayer.play()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    Log.e("LIFECYCLE", "paused")
                    exoPlayer.stop()
                }

                else -> {}
            }
        }
        lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycle.removeObserver(observer)
        }
    })
}