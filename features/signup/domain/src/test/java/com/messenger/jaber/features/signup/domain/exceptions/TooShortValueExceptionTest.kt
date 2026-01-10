package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class TooShortValueExceptionTest {
    @Test
    fun `inputField is propagated`() {
        val field = InputField.Login
        val value = "ab"

        val exception = TooShortValueException(
            inputField = field,
            value = value
        )

        assertEquals(field, exception.inputField)
    }

    @Test
    fun `message is formatted correctly`() {
        val field = InputField.Login
        val value = "ab"

        val exception = TooShortValueException(
            inputField = field,
            value = value
        )

        val expectedMessage =
            "$field is too short (min required chars = ${field.minChars}), current length = ${value.length}"

        assertEquals(expectedMessage, exception.message)
    }

    @Test
    fun `getLocalizedErrorMessage delegates to stringProvider`() {
        val field = InputField.Login
        val value = "ab"
        val localizedMessage = "Login is too short"

        val stringProvider = mockk<SignUpStringProvider>()
        every { stringProvider.tooShortValueError(field) } returns localizedMessage

        val exception = TooShortValueException(
            inputField = field,
            value = value
        )

        val result = exception.getLocalizedErrorMessage(stringProvider)

        assertEquals(localizedMessage, result)
    }
}