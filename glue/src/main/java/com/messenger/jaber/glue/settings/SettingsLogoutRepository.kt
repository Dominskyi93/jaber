package com.messenger.jaber.glue.settings

import com.messenger.jaber.data.AccountsDataRepository
import com.messenger.jaber.settings.domain.repositories.LogoutRepository
import javax.inject.Inject

class SettingsLogoutRepository @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository
) : LogoutRepository {
    override suspend fun logout() {
        accountsDataRepository.logout()
    }
}