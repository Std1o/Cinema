package com.example.cinema.presentation.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
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
            navGraph = NavGraphs.root
        )
    }
}