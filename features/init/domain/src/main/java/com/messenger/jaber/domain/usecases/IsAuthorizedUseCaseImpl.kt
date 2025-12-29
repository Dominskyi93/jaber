package com.messenger.jaber.domain.usecases

import com.messenger.jaber.domain.IsAuthorizedUseCase
import com.messenger.jaber.domain.repositories.AuthRepository
import javax.inject.Inject

internal class IsAuthorizedUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : IsAuthorizedUseCase {
    override suspend fun invoke(): Boolean {
        return authRepository.isAuthorized()
    }
}