package com.messenger.jaber.signin.domain.usecases

import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.entities.validate
import com.messenger.jaber.signin.domain.repositories.SignInRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class SignInUseCaseTest {
//    private val repository = mockk<SignInRepository>()
//    private val useCase = SignInUseCaseImpl(repository)
//
//    @Test
//    fun `GIVEN valid credentials WHEN invoke THEN validate and signIn called`() = runTest {
//        val credentials = mockk<Credentials>()
//
//        every { credentials.validate() } just runs
//        coEvery { repository.signIn(credentials) } returns Result.success(Unit)
//
//        val result = useCase.invoke(credentials)
//
//        coVerify(exactly = 1) { credentials.validate() }
//        coVerify(exactly = 1) { repository.signIn(credentials) }
//        assertEquals(Result.success(Unit), result)
//    }
//
//    @Test(expected = IllegalArgumentException::class)
//    fun `GIVEN invalid credentials WHEN invoke THEN validation exception thrown and repository not called`() =
//        runTest {
//            val credentials = mockk<Credentials>()
//            val exception = IllegalArgumentException()
//
//            every { credentials.validate() } throws exception
//
//            useCase.invoke(credentials)
//
//            coVerify(exactly = 0) {
//                repository.signIn(any())
//            }
//        }
//
//    @Test
//    fun `GIVEN repository failure WHEN invoke THEN failure result returned`() = runTest {
//        val credentials = mockk<Credentials>()
//        val failure = Result.failure<Unit>(RuntimeException("network error"))
//
//        every { credentials.validate() } just runs
//        coEvery { repository.signIn(credentials) } returns failure
//
//        val result = useCase.invoke(credentials)
//
//        assertEquals(failure, result)
//    }
}