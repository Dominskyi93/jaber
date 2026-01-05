package com.messenger.jaber.data.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.messenger.jaber.core.data.network.interceptors.AuthTokenProvider
import com.messenger.jaber.data.SessionManager
import com.messenger.jaber.data.session.entities.AuthDataToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class SessionManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SessionManager, AuthTokenProvider {

    override fun getToken(): Flow<AuthDataToken> {
        return dataStore.data.map { preferences ->
            preferences[tokenKey]?.let(AuthDataToken::Default)
                ?: AuthDataToken.Empty
        }
    }

    override suspend fun saveToken(token: AuthDataToken) {
        dataStore.edit { preferences ->
            when (token) {
                is AuthDataToken.Default -> preferences[tokenKey] = token.accessToken
                AuthDataToken.Empty -> preferences.remove(tokenKey)
            }
        }
    }

    override fun provideToken(): String? {
        return runBlocking {
            (getToken().first() as? AuthDataToken.Default)
                ?.accessToken
        }

    }

    private companion object {
        val tokenKey = stringPreferencesKey("token")
    }
}