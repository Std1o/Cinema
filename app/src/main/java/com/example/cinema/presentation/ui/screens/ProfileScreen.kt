package com.example.cinema.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cinema.presentation.viewmodel.MoviesViewModel
import com.example.cinema.presentation.viewmodel.ProfileViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>
@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(vertical = 50.dp, horizontal = 20.dp)) {
        Text("ФИО: ${uiState.fio}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("ID: ${uiState.id}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("E-mail: ${uiState.email}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("Телефон: ${uiState.phone}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("Подписка: ${uiState.subscribeType.value}")
        Spacer(modifier = Modifier.height(8.dp))
    }
}