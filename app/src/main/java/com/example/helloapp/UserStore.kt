package com.example.helloapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.helloapp.UserStore.Companion.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class UserStore(private val context: Context, s: String) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val USER_TOKEN_KEY = stringPreferencesKey("UserName")
        private val USER_TOKEN_KEY1 = stringPreferencesKey("UserEmail")
        private val USER_TOKEN_KEY2 = stringPreferencesKey("id")
    }


    val getName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY] ?: "Name"
    }
    val getEmail: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY1] ?: "email_prefs"
    }
    val getId: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY2] ?: "id"
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    suspend fun saveToken1(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY1] = token
        }
    }

    suspend fun saveToken2(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY2] = token
        }
    }

//Load button

    suspend fun loadUserName(): String {
        return context.dataStore.data.map { preferences ->
            preferences[USER_TOKEN_KEY] ?: ""
        }.first()
    }
    suspend fun loadEmailId(): String {
        return context.dataStore.data.map { preferences ->
            preferences[USER_TOKEN_KEY1] ?: ""
        }.first()
    }
    suspend fun loadStudentID(): String {
        return context.dataStore.data.map { preferences ->
            preferences[USER_TOKEN_KEY2] ?: ""
        }.first()
    }
}