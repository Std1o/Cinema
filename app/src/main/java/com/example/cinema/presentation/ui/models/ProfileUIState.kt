package com.example.cinema.presentation.ui.models

import com.example.cinema.domain.enums.SubscribeType
import com.example.cinema.domain.models.Subscription
import java.util.UUID

data class ProfileUIState(
    val fio: String = "Зубенко Михаил Петрович",
    val id: String = "",
    val email: String = "",
    val phone: String = "88005553535",
    val subscribeType: SubscribeType = SubscribeType.PREMIUM,
    val subscriptions: List<Subscription> = emptyList(),
    val isLoading: Boolean = true
)
