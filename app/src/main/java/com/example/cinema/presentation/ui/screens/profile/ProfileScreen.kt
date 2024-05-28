package com.example.cinema.presentation.ui.screens.profile

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cinema.common.iTems
import com.example.cinema.presentation.ui.components.ConfirmationDialog
import com.example.cinema.presentation.ui.components.LoadingIndicator
import com.example.cinema.presentation.ui.theme.Purple40
import com.example.cinema.presentation.viewmodel.ProfileViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>
@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    var showLogoutDialog by rememberSaveable { mutableStateOf(false) }
    val activity = LocalContext.current as Activity
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.getAllSubscriptions()
                    showBottomSheet = true
                },
                shape = CircleShape,
                modifier = Modifier
                    .height(80.dp)
                    .padding(bottom = 50.dp, end = 4.dp),
                backgroundColor = Purple40
            ) {
                Text(
                    "Добавить подписку",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
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

                Text(
                    text = "Подписки",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                LazyColumn {
                    iTems(uiState.subscriptions, key = { it }) { subscription ->
                        val shape = RoundedCornerShape(5.dp)
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            shape = shape,
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 10.dp, horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = subscription.img,
                                    contentDescription = "Translated description of what the image contains",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentScale = ContentScale.FillWidth
                                )
                                Text(
                                    text = subscription.name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
                }
            }

            OutlinedButton(
                onClick = { showLogoutDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(200.dp)
                    .height(50.dp)
                    .padding(bottom = 8.dp),
                border = BorderStroke(1.dp, Purple40),
            ) {
                Text(text = "Выйти", fontSize = 16.sp, color = Purple40)
            }
        }
    }
    if (showLogoutDialog) {
        ConfirmationDialog(
            onDismissRequest = { showLogoutDialog = false },
            onConfirmation = {
                showLogoutDialog = false
                viewModel.logout()
                activity.recreate()
            },
            dialogText = "Выйти?"
        )
    }
    if (uiState.isLoading) LoadingIndicator()

    if (showBottomSheet && uiState.allSubscriptions.isNotEmpty()) {
        SubscriptionAdding(
            subscriptions = uiState.allSubscriptions,
            onDismissRequest = { showBottomSheet = false }) {
            viewModel.onSubscriptionAdded(it)
        }
    }
}