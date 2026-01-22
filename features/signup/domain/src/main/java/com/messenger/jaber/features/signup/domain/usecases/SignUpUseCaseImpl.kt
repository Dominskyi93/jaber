package com.messenger.jaber.features.signup.domain.usecases

import com.messenger.jaber.features.signup.domain.SignUpUseCase
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.repositories.CreateAccountRepository
import com.messenger.jaber.features.signup.domain.repositories.CreateUserRepository
import com.messenger.jaber.features.signup.domain.validators.NewAccountValidator
import javax.inject.Inject

internal class SignUpUseCaseImpl @Inject constructor(
    private val newAccountValidator: NewAccountValidator,
    private val createAccountRepository: CreateAccountRepository,
    private val createUserRepository: CreateUserRepository
) : SignUpUseCase {
    override suspend fun invoke(account: NewAccount) {
        val validationResult = newAccountValidator.validate(account)
        when (validationResult) {
            is ValidationResult.Failure -> {
                throw validationResult.exceptions.first()
            }

            ValidationResult.Success -> {
                val uid = createAccountRepository.createFirebaseAccount(account)
                createUserRepository.createUserData(account, uid)
            }
        }
    }
}