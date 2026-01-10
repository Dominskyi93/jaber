package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class InvalidRangeExceptionTest {
    @Test
    fun `getLocalizedErrorMessage delegates to stringProvider`() {
        val inputField = InputField.Age
        val value = "150"
        val expectedMessage = "Age is out of range"

        val stringProvider = mockk<SignUpStringProvider>()
        every { stringProvider.invalidRangeError(inputField) } returns expectedMessage

        val exception = InvalidRangeException(
            inputField = inputField,
            value = value
        )

        val result = exception.getLocalizedErrorMessage(stringProvider)

        assertEquals(expectedMessage, result)
    }

    @Test
    fun `exception message is constructed correctly`() {
        val inputField = InputField.Age
        val value = "150"

        val exception = InvalidRangeException(
            inputField = inputField,
            value = value
        )

        assertEquals(
            "$inputField should be in range ${inputField.range}.",
            exception.message
        )
    }
}