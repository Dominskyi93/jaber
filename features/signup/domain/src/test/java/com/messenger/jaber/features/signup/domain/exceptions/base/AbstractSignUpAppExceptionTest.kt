package com.messenger.jaber.features.signup.domain.exceptions.base

import com.messenger.core.essentials.resources.StringProviderStore
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class AbstractSignUpAppExceptionTest {
    private class TestException(
        private val localized: String
    ) : AbstractSignUpAppException("test") {

        override fun getLocalizedErrorMessage(
            stringProvider: SignUpStringProvider
        ): String = localized
    }

    @Test
    fun `delegates localization via StringProviderStore`() {
        val expected = "Localized error"

        val signUpProvider = mockk<SignUpStringProvider>()

        val store = mockk<StringProviderStore>()
        every {
            store.invoke<SignUpStringProvider>()
        } returns signUpProvider

        val exception = TestException(expected)

        val result = exception.getLocalizedErrorMessage(store)

        assertEquals(expected, result)
    }
}