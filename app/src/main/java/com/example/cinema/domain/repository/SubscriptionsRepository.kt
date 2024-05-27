package com.example.cinema.domain.repository

import com.example.cinema.domain.models.Subscription

interface SubscriptionsRepository {
    fun getSubscriptions(): List<Subscription>
}