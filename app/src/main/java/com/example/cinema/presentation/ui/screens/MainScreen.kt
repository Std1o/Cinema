package com.example.cinema.presentation.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>(start = true) // sets this as a destination of the "root" nav graph
@Composable
fun MainScreen(navigator: DestinationsNavigator, modifier: Modifier) {
    Text(
        text = "Main Screen",
        modifier = modifier
    )
}