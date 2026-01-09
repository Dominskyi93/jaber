package com.messenger.jaber.features.signup.domain.validators

import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.exceptions.LoginAlreadyExistException
import com.messenger.jaber.features.signup.domain.exceptions.PasswordMismatchException
import com.messenger.jaber.features.signup.domain.repositories.LoginAvailabilityRepository
import com.messenger.jaber.features.signup.domain.stubs.createNewAccount
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NewAccountValidatorTest {
    @MockK
    private lateinit var repository: LoginAvailabilityRepository
    private lateinit var validator: NewAccountValidatorImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        validator = NewAccountValidatorImpl(repository)
    }

    @Test
    fun `returns Success when account data is valid`() = runTest {
        val account = createNewAccount()

        coEvery { repository.isLoginAvailable(account.login) } returns false

        val result = validator.validate(account)

        assertTrue(result is ValidationResult.Success)

        coVerify(exactly = 1) {
            repository.isLoginAvailable(account.login)
        }
    }

    @Test
    fun `returns PasswordMismatchException when passwords do not match`() = runTest {
        val account = createNewAccount(repeatPassword = "different")

        coEvery { repository.isLoginAvailable(account.login) } returns false

        val result = validator.validate(account)

        assertTrue(
            result is ValidationResult.Failure &&
                    result.exceptions.any { it is PasswordMismatchException }
        )
    }

    @Test
    fun `returns LoginAlreadyExistException when login is not available`() = runTest {
        val account = createNewAccount()

        coEvery { repository.isLoginAvailable(account.login) } returns true

        val result = validator.validate(account)

        assertTrue(
            result is ValidationResult.Failure &&
                    result.exceptions.any { it is LoginAlreadyExistException }
        )
    }

    @Test
    fun `combines password mismatch and login already exists errors`() = runTest {
        val account = createNewAccount(repeatPassword = "different")

        coEvery { repository.isLoginAvailable(account.login) } returns true

        val result = validator.validate(account)

        assertTrue(result is ValidationResult.Failure)

        result as ValidationResult.Failure

        assertTrue(result.exceptions.any { it is PasswordMismatchException })
        assertTrue(result.exceptions.any { it is LoginAlreadyExistException })
    }

}