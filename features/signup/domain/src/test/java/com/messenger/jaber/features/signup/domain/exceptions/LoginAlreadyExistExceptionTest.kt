package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginAlreadyExistExceptionTest {
    @Test
    fun `exception message contains login`() {
        val login = "test_login"

        val exception = LoginAlreadyExistException(login)

        assertEquals(
            "Login \"$login\" already exists",
            exception.message
        )
    }

    @Test
    fun `inputField is Login`() {
        val exception = LoginAlreadyExistException("any")

        assertEquals(
            InputField.Login,
            exception.inputField
        )
    }

    @Test
    fun `getLocalizedErrorMessage delegates to stringProvider`() {
        val expectedMessage = "Login already exists"

        val stringProvider = mockk<SignUpStringProvider>()
        every { stringProvider.loginAlreadyExistsError } returns expectedMessage

        val exception = LoginAlreadyExistException("user123")

        val result = exception.getLocalizedErrorMessage(stringProvider)

        assertEquals(expectedMessage, result)
    }

}