package com.example.cinema.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.cinema.presentation.ui.components.LoadingIndicator
import com.example.cinema.presentation.ui.screens.main.MainScreen
import com.example.cinema.presentation.ui.theme.CinemaTheme
import com.example.cinema.presentation.viewmodel.MainActivityViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel = hiltViewModel<MainActivityViewModel>()
                    val uiState by viewModel.uiState.collectAsState()
                    if (uiState.isUserAuthorized) {
                        MainScreen()
                    } else if (!uiState.isLoading) {
                        DestinationsNavHost(navGraph = NavGraphs.authRoute)
                    }

                    if (uiState.isLoading) LoadingIndicator()
                }
            }
        }
    }
}