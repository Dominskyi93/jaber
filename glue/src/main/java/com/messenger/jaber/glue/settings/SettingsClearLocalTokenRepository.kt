package com.messenger.jaber.glue.settings

import com.messenger.jaber.data.SessionManager
import com.messenger.jaber.data.session.entities.AuthDataToken
import com.messenger.jaber.settings.domain.repositories.ClearLocalTokenRepository
import javax.inject.Inject

class SettingsClearLocalTokenRepository @Inject constructor(
    private val sessionManager: SessionManager
) : ClearLocalTokenRepository {
    override suspend fun clearLocalToken() {
        sessionManager.saveToken(AuthDataToken.Empty)
    }
}