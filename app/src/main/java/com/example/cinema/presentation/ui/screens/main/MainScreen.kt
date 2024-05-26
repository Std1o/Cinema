package com.example.cinema.presentation.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cinema.presentation.ui.screens.main.BottomBar
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
        //...
    ) { innerPadding ->
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(innerPadding)
        )
    }
}