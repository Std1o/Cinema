package com.example.cinema.data.repository

import com.example.cinema.domain.models.Subscription
import com.example.cinema.domain.repository.SubscriptionsRepository
import javax.inject.Inject

class SubscriptionsRepositoryImpl @Inject constructor() : SubscriptionsRepository {
    override fun getSubscriptions(): List<Subscription> = subscriptions

    private val subscriptions = listOf(
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
}