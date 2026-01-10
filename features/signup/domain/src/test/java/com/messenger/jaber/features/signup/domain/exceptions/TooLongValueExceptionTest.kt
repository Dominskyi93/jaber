package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class TooLongValueExceptionTest {
    @Test
    fun `inputField is propagated`() {
        val field = InputField.Login
        val value = "very_long_login_value"

        val exception = TooLongValueException(
            inputField = field,
            value = value
        )

        assertEquals(field, exception.inputField)
    }

    @Test
    fun `message is formatted correctly`() {
        val field = InputField.Login
        val value = "very_long_login_value"

        val exception = TooLongValueException(
            inputField = field,
            value = value
        )

        val expectedMessage =
            "$field is too long (max required chars = ${field.maxChars}), current length = ${value.length}"

        assertEquals(expectedMessage, exception.message)
    }

    @Test
    fun `getLocalizedErrorMessage delegates to stringProvider`() {
        val field = InputField.Login
        val value = "very_long_login_value"
        val localizedMessage = "Login is too long"

        val stringProvider = mockk<SignUpStringProvider>()
        every { stringProvider.tooLongValueError(field) } returns localizedMessage

        val exception = TooLongValueException(
            inputField = field,
            value = value
        )

        val result = exception.getLocalizedErrorMessage(stringProvider)

        assertEquals(localizedMessage, result)
    }
}