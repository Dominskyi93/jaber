package com.messenger.jaber.features.signup.domain.validators

import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.entities.ValidationResult.Companion.combined
import com.messenger.jaber.features.signup.domain.entities.toFieldValues
import com.messenger.jaber.features.signup.domain.exceptions.LoginAlreadyExistException
import com.messenger.jaber.features.signup.domain.exceptions.PasswordMismatchException
import com.messenger.jaber.features.signup.domain.repositories.LoginAvailabilityRepository
import javax.inject.Inject

internal interface NewAccountValidator {
    suspend fun validate(account: NewAccount): ValidationResult
}

internal class NewAccountValidatorImpl @Inject constructor(
    private val loginAvailabilityRepository: LoginAvailabilityRepository
) : NewAccountValidator {
    override suspend fun validate(account: NewAccount): ValidationResult {
        val fieldValues = account.toFieldValues()
        var validationResults = fieldValues
            .map { it.validate() }
            .combined()

        if (account.password != account.repeatPassword) {
            validationResults += PasswordMismatchException()
        }

        if (!loginAvailabilityRepository.isLoginAvailable(account.login)) {
            validationResults += LoginAlreadyExistException(account.login)
        }
        return validationResults
    }

}