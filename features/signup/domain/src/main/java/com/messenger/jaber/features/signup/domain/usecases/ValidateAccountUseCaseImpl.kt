package com.messenger.jaber.features.signup.domain.usecases

import com.messenger.jaber.features.signup.domain.ValidateAccountUseCase
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.validators.NewAccountValidator
import javax.inject.Inject

/**
 * Validate data in newAccount
 */
internal class ValidateAccountUseCaseImpl @Inject constructor(
    private val newAccountValidator: NewAccountValidator
) : ValidateAccountUseCase {
    override suspend fun invoke(account: NewAccount): ValidationResult {
        return newAccountValidator.validate(account)
    }
}