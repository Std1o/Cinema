package com.example.cinema.data.repository

import com.example.cinema.domain.models.Subscription
import com.example.cinema.domain.repository.SubscriptionsRepository
import javax.inject.Inject

class SubscriptionsRepositoryImpl @Inject constructor() : SubscriptionsRepository {
    override fun getSubscriptions(): List<Subscription> = userSubscriptions

    private val userSubscriptions = listOf(
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

    private val allSubscriptions = listOf(
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/50064/c965e7ae1145db8f4a5d636d8a8682ad07b486d3/192x192",
            name = "Комедии"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/56833/ed83c13d1ddbd91dbb34d35b3936c32f1ceef2b2/192x192",
            name = "Мультфильмы"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/50064/a88a3adacd414135e7f67d6459f462302844882b/192x192",
            name = "Ужасы"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/118781/2d1173df7d33adb29c73cfaaa3d014e48103223a/192x192",
            name = "Фантастика"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/128809/bc0da196556282793b39f4bd77143d9d7a7a353b/192x192",
            name = "Триллеры"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/61205/07b81fddd36a916e3c1dad941de8e47ecf896c7e/192x192",
            name = "Боевики"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/128809/58e8f588c62ec66988c914314f32e38ab12f3fb2/192x192",
            name = "Мелодрамы"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/50064/546ba464afc21764e58be3987df5063a0a2f9da9/192x192",
            name = "Детективы"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/9784475/c7cc108a-0689-4486-8bf0-c0cc7eecabf4/192x192",
            name = "Приключения"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/118781/978442eab5efbae3978771d335a13a5f0289b85e/192x192",
            name = "Фэнтези"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/56833/3f2342b15393fb66d99757e9e0384ffb68fbfe02/192x192",
            name = "Военные"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/10893610/38234972-7ff0-4fc0-82e3-c921c714f033/192x192",
            name = "Семейные"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/998550/02b1907eef5123b57eea76be2aab126ab5888717/192x192",
            name = "Аниме"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/10809116/94b2b524-6a5d-4a16-bc60-25bc8d39216f/192x192",
            name = "Исторические"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-bunker/49769/efe0f7420bc7d970c0a8002e6d3e6d484754cdd5/192x192",
            name = "Драмы"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/9784475/a34c5c6b-523d-4229-8a54-d3c4305f0975/192x192",
            name = "Документальные"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/10835644/e03575fd-138f-4391-8c2f-82ca10c42c3b/192x192",
            name = "Детские"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/10900341/50174193-8e80-4913-8644-c4fc8305e378/192x192",
            name = "Криминал"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/10893610/37a33d2e-2559-4fc4-875e-7ae39971af71/192x192",
            name = "Биографии"
        ),
        Subscription(
            img = "https://avatars.mds.yandex.net/get-kinopoisk-image/10809116/3703ba45-3397-4e03-81f5-c092dc2ce0a3/192x192",
            name = "Короткометражки"
        ),
    )
}