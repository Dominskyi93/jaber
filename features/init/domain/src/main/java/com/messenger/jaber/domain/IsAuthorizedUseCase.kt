package com.messenger.jaber.domain

interface IsAuthorizedUseCase {
    suspend operator fun invoke(): Boolean
}