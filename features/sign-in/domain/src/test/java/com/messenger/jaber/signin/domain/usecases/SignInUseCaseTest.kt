package com.messenger.jaber.signin.domain.usecases

import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.repositories.SignInRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignInUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: SignInRepository
    private lateinit var useCase: SignInUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = SignInUseCaseImpl(
            signInRepository = repository
        )
    }

    @Test
    fun `signIn delegates to repository`() = runBlocking {
        val credentials = Credentials.Default(
            login = "test@mail.com", password = "12345678"
        )

        useCase.invoke(credentials)

        coVerify(exactly = 1) {
            repository.signIn(credentials)
        }
    }


}