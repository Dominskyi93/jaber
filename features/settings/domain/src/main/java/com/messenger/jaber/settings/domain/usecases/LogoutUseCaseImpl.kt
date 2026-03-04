package com.messenger.jaber.settings.domain.usecases

import com.messenger.jaber.settings.domain.LogoutUseCase
import com.messenger.jaber.settings.domain.effects.LogoutUserChoices
import com.messenger.jaber.settings.domain.repositories.ClearLocalTokenRepository
import com.messenger.jaber.settings.domain.repositories.LogoutRepository
import javax.inject.Inject

internal class LogoutUseCaseImpl @Inject constructor(
    private val logoutRepository: LogoutRepository,
    private val clearLocalTokenRepository: ClearLocalTokenRepository,
    private val userChoices: LogoutUserChoices
) : LogoutUseCase {
    override suspend fun invoke(): Boolean {
        val isConfirmed = userChoices.confirmLogout()
        if (isConfirmed) {
            logoutRepository.logout()
            clearLocalTokenRepository.clearLocalToken()
        }
        return isConfirmed
    }
}