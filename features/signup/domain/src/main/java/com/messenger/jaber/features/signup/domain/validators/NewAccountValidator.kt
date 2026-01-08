package com.messenger.jaber.features.signup.domain.validators

import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.repositories.LoginAvailabilityRepository
import javax.inject.Inject

internal interface NewAccountValidator {
    suspend fun validate(account: NewAccount): ValidationResult
}

internal class NewAccountValidatorImpl @Inject constructor(
    private val loginAvailabilityRepository: LoginAvailabilityRepository
) : NewAccountValidator {
    override suspend fun validate(account: NewAccount): ValidationResult {


    }

}