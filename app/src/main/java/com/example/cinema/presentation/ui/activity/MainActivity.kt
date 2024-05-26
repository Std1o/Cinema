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
import com.example.cinema.presentation.ui.screens.BottomBar
import com.example.cinema.presentation.ui.screens.MainScreen
import com.example.cinema.presentation.ui.theme.CinemaTheme
import com.example.cinema.presentation.viewmodel.MainActivityViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    val viewModel = hiltViewModel<MainActivityViewModel>()
                    val isUserAuthorized by viewModel.isUserAuthorized.collectAsState()
                    if (isUserAuthorized) {
                        MainScreen()
                    } else {
                        DestinationsNavHost(navGraph = NavGraphs.authRoute)
                    }
                }
            }
        }
    }
}