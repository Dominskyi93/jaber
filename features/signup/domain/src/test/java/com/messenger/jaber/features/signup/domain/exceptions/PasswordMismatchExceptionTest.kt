package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class PasswordMismatchExceptionTest {
    @Test
    fun `message is correct`() {
        val exception = PasswordMismatchException()

        assertEquals(
            "Password mismatch error!",
            exception.message
        )
    }

    @Test
    fun `inputField is RepeatPassword`() {
        val exception = PasswordMismatchException()

        assertEquals(
            InputField.RepeatPassword,
            exception.inputField
        )
    }

    @Test
    fun `getLocalizedErrorMessage delegates to stringProvider`() {
        val expected = "Passwords do not match"

        val stringProvider = mockk<SignUpStringProvider>()
        every { stringProvider.passwordMismatchError } returns expected

        val exception = PasswordMismatchException()

        val result = exception.getLocalizedErrorMessage(stringProvider)

        assertEquals(expected, result)
    }

}