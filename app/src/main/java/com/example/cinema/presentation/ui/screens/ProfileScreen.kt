package com.example.cinema.presentation.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cinema.common.iTems
import com.example.cinema.domain.models.Subscription
import com.example.cinema.presentation.ui.theme.Purple40
import com.example.cinema.presentation.viewmodel.ProfileViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>
@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
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

            val subscriptions = listOf(
                Subscription(
                    img = "https://avatars.mds.yandex.net/get-bunker/50064/c965e7ae1145db8f4a5d636d8a8682ad07b486d3/192x192",
                    name = "Комедии"
                ),
                Subscription(
                    img = "https://avatars.mds.yandex.net/get-bunker/61205/07b81fddd36a916e3c1dad941de8e47ecf896c7e/192x192",
                    name = "Боевики"
                ),
                Subscription(
                    img = "https://avatars.mds.yandex.net/get-bunker/50064/546ba464afc21764e58be3987df5063a0a2f9da9/192x192",
                    name = "Детективы"
                ),
                Subscription(
                    img = "https://avatars.mds.yandex.net/get-bunker/118781/2d1173df7d33adb29c73cfaaa3d014e48103223a/192x192",
                    name = "Фантастика"
                ),
            )
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
                iTems(subscriptions, key = { it }) { subscription ->
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
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomCenter).width(200.dp).height(50.dp).padding(bottom = 8.dp),
            border = BorderStroke(1.dp, Purple40),
        ) {
            Text(text = "Выйти", fontSize = 16.sp, color = Purple40)
        }
    }
}