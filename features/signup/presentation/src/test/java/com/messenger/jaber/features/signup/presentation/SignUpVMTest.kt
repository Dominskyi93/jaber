package com.messenger.jaber.features.signup.presentation

import com.elveum.container.Container
import com.elveum.container.unwrap
import com.messenger.jaber.features.signup.domain.SignUpUseCase
import com.messenger.jaber.features.signup.domain.ValidateAccountUseCase
import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import com.uandcode.flowtest.FlowTestScope
import com.uandcode.flowtest.TestFlowCollector
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
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

            val lastState = collector.lastState()
            assertEquals(expectedIsInProgress, lastState.isSignUpInProgress)
            assertEquals(expectedErrors, lastState.errorMessages)
        }

    private fun FlowTestScope.startCollectingState() = viewModel.stateFlow.startCollecting()
    private fun TestFlowCollector<Container<SignUpVM.State>>.lastState() = lastItem.unwrap()

}