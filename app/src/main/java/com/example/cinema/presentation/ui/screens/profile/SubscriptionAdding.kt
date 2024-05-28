package com.example.cinema.presentation.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cinema.common.iTems
import com.example.cinema.domain.models.Subscription
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionAdding(
    subscriptions: List<Subscription>,
    onDismissRequest: () -> Unit,
    onClick: (Subscription) -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        LazyColumn {
            iTems(subscriptions, key = { it }) { subscription ->
                val shape = RoundedCornerShape(5.dp)
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    shape = shape,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            onClick(subscription)
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) onDismissRequest()
                            }
                        }
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
}