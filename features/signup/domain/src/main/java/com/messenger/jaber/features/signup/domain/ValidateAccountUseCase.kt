package com.messenger.jaber.features.signup.domain

import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult

interface ValidateAccountUseCase {
    suspend operator fun invoke(account: NewAccount): ValidationResult
}