package com.messenger.jaber.features.signup.domain.usecases

import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.repositories.CreateAccountRepository
import com.messenger.jaber.features.signup.domain.repositories.CreateUserRepository
import com.messenger.jaber.features.signup.domain.validators.NewAccountValidator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignUpUseCaseTest {
    @MockK
    private lateinit var validator: NewAccountValidator

    @RelaxedMockK
    private lateinit var createAccountRepository: CreateAccountRepository

    @RelaxedMockK
    private lateinit var createUserRepository: CreateUserRepository

    private lateinit var useCase: SignUpUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = SignUpUseCaseImpl(
            newAccountValidator = validator,
            createAccountRepository = createAccountRepository,
            createUserRepository = createUserRepository
        )
    }

    @Test
    fun `creates account when validation is successful`() = runBlocking {
        val account = mockk<NewAccount>()

        coEvery { validator.validate(account) } returns ValidationResult.Success

        useCase.invoke(account)

        coVerify(exactly = 1) {
            createAccountRepository.createFirebaseAccount(account)
        }
    }

    @Test
    fun `throws first validation exception and does not create account`() = runBlocking {
        val account = mockk<NewAccount>()
        val exception = mockk<AbstractValidationException>()

        val failure = ValidationResult.Failure(
            exceptions = listOf(exception)
        )

        coEvery { validator.validate(account) } returns failure

        useCase.invoke(account)

        coVerify(exactly = 0) {
            createAccountRepository.createFirebaseAccount(any())
        }
    }
}