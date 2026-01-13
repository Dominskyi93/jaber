package com.messenger.jaber.signin.domain.exceptions

import com.messenger.jaber.signin.domain.resources.SignInStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class InvalidCredentialsExceptionTest {
    @Test
    fun `uses correct default message`() {
        val exception = InvalidCredentialsException()

        assertEquals(
            "Invalid login or password",
            exception.message
        )
    }

    @Test
    fun `returns localized invalid credentials error`() {
        val provider = mockk<SignInStringProvider>()

        every { provider.invalidCredentialsError } returns "Invalid login and/or password"

        val exception = InvalidCredentialsException()

        val result = exception.getLocalizedErrorMessage(provider)

        assertEquals("Invalid login and/or password", result)
    }
}