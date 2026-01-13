package com.messenger.jaber.signin.domain.exceptions

import com.messenger.jaber.signin.domain.entities.InputField
import com.messenger.jaber.signin.domain.resources.SignInStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class EmptyFieldExceptionTest {

    @Test
    fun `stores input field`() {
        val field = InputField.LOGIN

        val exception = EmptyFieldException(field)

        assertEquals(field, exception.inputField)
    }

    @Test
    fun `uses correct default message`() {
        val exception = EmptyFieldException(InputField.PASSWORD)

        assertEquals(
            "Invalid login or password",
            exception.message
        )
    }

    @Test
    fun `returns localized error message from string provider`() {
        val field = InputField.LOGIN
        val provider = mockk<SignInStringProvider>()

        every { provider.emptyFieldError(field) } returns "${field.fieldName} is empty"

        val exception = EmptyFieldException(field)

        val result = exception.getLocalizedErrorMessage(provider)

        assertEquals("${field.fieldName} is empty", result)
    }

}