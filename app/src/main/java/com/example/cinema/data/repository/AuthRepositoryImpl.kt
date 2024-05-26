package com.example.cinema.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.cinema.common.Constants.IS_USER_AUTHORIZED_KEY
import com.example.cinema.common.dataStore
import com.example.cinema.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    AuthRepository {
    override suspend fun isUserAuthorized() = context.dataStore.data.map { preferences ->
        preferences[IS_USER_AUTHORIZED] ?: false
    }

    override suspend fun setUserAuthorized(authorized: Boolean) {
        context.dataStore.edit { settings ->
            settings[IS_USER_AUTHORIZED] = authorized
        }
    }

    companion object {
        private val IS_USER_AUTHORIZED = booleanPreferencesKey(IS_USER_AUTHORIZED_KEY)
    }
}