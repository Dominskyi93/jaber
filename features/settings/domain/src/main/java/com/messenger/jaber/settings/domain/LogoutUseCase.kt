package com.messenger.jaber.settings.domain

interface LogoutUseCase {
    suspend operator fun invoke(): Boolean
}