package com.example.cinema.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun isUserAuthorized(): Flow<Boolean>
    suspend fun setUserAuthorized(authorized: Boolean)
}