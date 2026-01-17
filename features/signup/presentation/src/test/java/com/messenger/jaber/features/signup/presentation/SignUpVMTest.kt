package com.messenger.jaber.features.signup.presentation

import com.elveum.container.Container
import com.elveum.container.unwrap
import com.messenger.jaber.features.signup.domain.SignUpUseCase
import com.messenger.jaber.features.signup.domain.ValidateAccountUseCase
import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import com.messenger.jaber.features.signup.presentation.SignUpVM.Companion.VALIDATION_PERIOD_MILLIS
import com.uandcode.flowtest.FlowTestScope
import com.uandcode.flowtest.TestFlowCollector
import io.mockk.awaits
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SignUpVMTest : AbstractViewModelTest<SignUpVM>() {

    @RelaxedMockK
    private lateinit var signUpUseCase: SignUpUseCase

    @RelaxedMockK
    private lateinit var validateAccountUseCase: ValidateAccountUseCase

    @RelaxedMockK
    private lateinit var router: SignUpRouter

    @RelaxedMockK
    private lateinit var stringProvider: SignUpStringProvider

    override fun createViewModel() = SignUpVM(
        signUpUseCase,
        validateAccountUseCase,
        router,
        stringProvider
    )

    @Test
    fun `GIVEN viewmodel is created WHEN instantiate THEN initial state is default`() =
        runFlowTest {
            val expectedIsInProgress = false
            val expectedErrors = emptyMap<InputField<*>, String>()

            val collector = startCollectingState()

            val lastState = collector.lastItem
            assertEquals(expectedIsInProgress, lastState.isSignUpInProgress)
            assertEquals(expectedErrors, lastState.errorMessages)
        }

    @Test
    fun `GIVEN any input account WHEN signUP is executed THEN render progress indicator`() =
        runFlowTest {
            val account = mockk<NewAccount>()
            coEvery { signUpUseCase.invoke(account) } just awaits
            val collector = startCollectingState()

            viewModel.executeAction(SignUpAction.SignUp(account))
            val lastState = collector.lastItem
            assertTrue(lastState.isSignUpInProgress)
        }

    @Test
    fun `GIVEN successful account creation WHEN signUp executed show congrats screen`() {
        val account = mockk<NewAccount>()
        coEvery { signUpUseCase.invoke(account) } just runs
        viewModel.executeAction(SignUpAction.SignUp(account))

        verify(exactly = 1) {
            router.launchCongrats()
        }
    }

    @Test
    fun `GIVEN failed account creation WHEN signUp executed THEN show congrats screen`() {
        val account = mockk<NewAccount>()
        coEvery { signUpUseCase.invoke(account) } throws IllegalStateException()
        viewModel.executeAction(SignUpAction.SignUp(account))
        verify(exactly = 0) {
            router.launchCongrats()
        }
    }

    @Test
    fun `GIVEN failed account creation WHEN signUp executed THEN handle exception by default`() {
        val account = mockk<NewAccount>()
        val expectedException = IllegalStateException()

        coEvery { signUpUseCase.invoke(account) } throws expectedException
        viewModel.executeAction(SignUpAction.SignUp(account))
        verify(exactly = 1) {
            exceptionHandler.handleException(refEq(expectedException))
        }
    }

    @Test
    fun `GIVEN failed account validation creation WHEN signUp executed THEN handle exception by default`() {
        val account = mockk<NewAccount>()
        val expectedException = mockk<AbstractValidationException>()

        coEvery { signUpUseCase.invoke(account) } throws expectedException
        viewModel.executeAction(SignUpAction.SignUp(account))
        verify(exactly = 1) {
            exceptionHandler.handleException(refEq(expectedException))
        }
    }

    @Test
    fun `GIVEN failed account validation creation WHEN signUp executed THEN render errors`() =
        runFlowTest {
            val account = mockk<NewAccount>(relaxed = true)
            val expectedException = mockk<AbstractValidationException>()
            val expectedInputField = InputField.RepeatPassword
            val expectedErrorMessage = "Error!"
            every { expectedException.inputField } returns InputField.RepeatPassword
            every { expectedException.getLocalizedErrorMessage(stringProvider) } returns expectedErrorMessage
            coEvery { signUpUseCase.invoke(account) } throws expectedException
            val collector = startCollectingState()
            viewModel.executeAction(SignUpAction.SignUp(account))
            val lastState = collector.lastItem
            assertEquals(
                mapOf(expectedInputField to expectedErrorMessage),
                lastState.errorMessages
            )
        }

    @Test
    fun `GIVEN first validation request WHEN validation executed THEN do not start validation before 1 second elapsed`() =
        runFlowTest {
            val account = mockk<NewAccount>()
            coEvery { validateAccountUseCase.invoke(account) } just awaits
            initializeViewModel()
            viewModel.executeAction(SignUpAction.Validate(account))
            advanceTimeBy(VALIDATION_PERIOD_MILLIS)
            coVerify(exactly = 0) {
                validateAccountUseCase.invoke(any())
            }
        }

    @Test
    fun `GIVEN first validation request WHEN validation executed THEN start validation after 1 second elapsed`() =
        runFlowTest {
            val account = mockk<NewAccount>()
            coEvery { validateAccountUseCase.invoke(account) } just awaits
            initializeViewModel()
            viewModel.executeAction(SignUpAction.Validate(account))
            advanceTimeBy(VALIDATION_PERIOD_MILLIS + 1)
            coVerify(exactly = 1) {
                validateAccountUseCase.invoke(any())
            }
        }

    @Test
    fun `GIVEN fast multiple validation requests WHEN validation executed THEN only last request executed`() =
        runFlowTest {
            val account = mockk<NewAccount>()
            val account1 = mockk<NewAccount>()
            val account2 = mockk<NewAccount>()
            coEvery { validateAccountUseCase.invoke(any()) } just awaits
            initializeViewModel()
            viewModel.executeAction(SignUpAction.Validate(account))
            advanceTimeBy(VALIDATION_PERIOD_MILLIS)
            viewModel.executeAction(SignUpAction.Validate(account1))
            advanceTimeBy(VALIDATION_PERIOD_MILLIS)
            viewModel.executeAction(SignUpAction.Validate(account2))
            advanceTimeBy(VALIDATION_PERIOD_MILLIS)
            coVerify(exactly = 1) {
                validateAccountUseCase.invoke(refEq(account))
            }

            advanceTimeBy(1)
            coVerify(exactly = 1) {
                validateAccountUseCase.invoke(refEq(account))
//                validateAccountUseCase.invoke(refEq(account1))
            }
            coVerify(exactly = 1) {
                validateAccountUseCase.invoke(refEq(account2))
            }
        }

    @Test
    fun `GIVEN error exists WHEN ClearError executed THEN error is removed`() =
        runFlowTest {
            val field = InputField.RepeatPassword
            val errorMessage = "Error"

            val account = mockk<NewAccount>(relaxed = true)
            val exception = mockk<AbstractValidationException>()

            every { exception.inputField } returns field
            every { exception.getLocalizedErrorMessage(stringProvider) } returns errorMessage
            coEvery { signUpUseCase.invoke(account) } throws exception

            val collector = startCollectingState()
            // trigger validation error
            viewModel.executeAction(SignUpAction.SignUp(account))
            // sanity check: error is visible
            assertEquals(
                mapOf(field to errorMessage),
                collector.lastItem.errorMessages
            )
            // act
            viewModel.executeAction(SignUpAction.ClearError(field))
            // assert
            assertTrue(collector.lastItem.errorMessages.isEmpty())
        }

    @Test
    fun `GIVEN error exists but disabled WHEN EnableErrorMessages executed THEN error becomes visible`() =
        runFlowTest {
            val field = InputField.RepeatPassword
            val errorMessage = "Error"

            val validationException = mockk<AbstractValidationException>()
            every { validationException.inputField } returns field
            every { validationException.getLocalizedErrorMessage(stringProvider) } returns errorMessage

            val validationResult = ValidationResult.Failure(listOf(validationException))
            coEvery { validateAccountUseCase.invoke(any()) } returns validationResult

            initializeViewModel()
            val collector = startCollectingState()

            val account = mockk<NewAccount>(relaxed = true)

            // validation populates allErrorMessages, but errors are NOT enabled
            viewModel.executeAction(SignUpAction.Validate(account))
            advanceTimeBy(VALIDATION_PERIOD_MILLIS + 1)

            assertTrue(
                "Error should not be visible before enabling",
                collector.lastItem.errorMessages.isEmpty()
            )
            // act
            viewModel.executeAction(SignUpAction.EnableErrorMessages(field))
            // assert
            assertEquals(
                mapOf(field to errorMessage),
                collector.lastItem.errorMessages
            )
        }

    @Test
    fun `GIVEN ValidationResult Success WHEN validation applied THEN errorMessages empty`() =
        runFlowTest {
            val account = mockk<NewAccount>()
            coEvery { validateAccountUseCase.invoke(account) } returns ValidationResult.Success

            initializeViewModel()
            val collector = startCollectingState()

            viewModel.executeAction(SignUpAction.Validate(account))
            advanceTimeBy(VALIDATION_PERIOD_MILLIS + 1)

            assertTrue(collector.lastItem.errorMessages.isEmpty())
        }


    private fun FlowTestScope.startCollectingState() = viewModel.stateFlow.startCollecting()
    private fun TestFlowCollector<Container<SignUpVM.State>>.lastItem() = lastItem.unwrap()

}