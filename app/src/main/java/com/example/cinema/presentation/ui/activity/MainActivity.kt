package com.example.cinema.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.cinema.presentation.ui.screens.MainScreen
import com.example.cinema.presentation.ui.theme.CinemaTheme
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemaTheme {
                val navController = rememberNavController()
                val navigator = navController.rememberDestinationsNavigator()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(navigator = navigator, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}