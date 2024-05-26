package com.example.cinema.presentation.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cinema.presentation.ui.navigation.AuthGraph
import com.ramcosta.composedestinations.annotation.Destination

@Destination<AuthGraph>(start = true)
@Composable
fun LoginScreen() {
    Text(text = "Auth")
}