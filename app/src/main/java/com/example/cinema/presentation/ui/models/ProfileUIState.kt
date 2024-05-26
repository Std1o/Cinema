package com.example.cinema.presentation.ui.models

import com.example.cinema.domain.enums.SubscribeType
import java.util.UUID

data class ProfileUIState(
    val fio: String = "Зубенко Михаил Петрович",
    val id: String = UUID.randomUUID().toString(),
    val email: String = "",
    val phone: String = "88005553535",
    val subscribeType: SubscribeType = SubscribeType.PREMIUM
)
