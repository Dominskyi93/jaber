package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class EmptyFieldExceptionTest {

    @Test
    fun `stores input field`() {
        val field = InputField.Login

        val exception = EmptyFieldException(field)

        assertSame(field, exception.inputField)
    }

    @Test
    fun `creates correct default message`() {
        val field = InputField.Password

        val exception = EmptyFieldException(field)

        assertEquals(
            "$field is not be empty",
            exception.message
        )
    }

    @Test
    fun `delegates localized message to string provider`() {
        val field = InputField.FirstName
        val expectedMessage = "First name is required"

        val stringProvider = mockk<SignUpStringProvider>()

        every { stringProvider.emptyFieldError(field) } returns expectedMessage

        val exception = EmptyFieldException(field)

        val result = exception.getLocalizedErrorMessage(stringProvider)

        assertEquals(expectedMessage, result)

        verify(exactly = 1) {
            stringProvider.emptyFieldError(field)
        }
    }
}