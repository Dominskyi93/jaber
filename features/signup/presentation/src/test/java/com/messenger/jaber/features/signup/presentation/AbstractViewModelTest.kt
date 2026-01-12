@file:OptIn(ExperimentalCoroutinesApi::class)

package com.messenger.jaber.features.signup.presentation

import com.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.messenger.core.essentials.logger.Logger
import com.messenger.jaber.core.presentation.WithCommonDependencies
import com.messenger.jaber.core.presentation.WithInitCallback
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.uandcode.flowtest.FlowTestScope
import com.uandcode.flowtest.runFlowTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

abstract class AbstractViewModelTest<T : AbstractViewModel> {

    @RelaxedMockK
    protected lateinit var logger: Logger
        private set

    @RelaxedMockK
    protected lateinit var exceptionHandler: ExceptionHandler
        private set

    protected lateinit var scope: TestScope
        private set

    protected lateinit var viewModel: T
        private set

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        scope = TestScope()
        Dispatchers.setMain(UnconfinedTestDispatcher(scope.testScheduler))
        viewModel = createViewModel()
        (viewModel as? WithCommonDependencies)?.initDependencies(logger, exceptionHandler)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    protected fun initializeViewModel() {
        (viewModel as? WithInitCallback)?.initializeViewModel(logger)
    }

    protected fun runTest(block: suspend TestScope.() -> Unit) = scope.runTest(testBody = block)

    protected fun runFlowTest(block: suspend FlowTestScope.() -> Unit) = scope.runFlowTest(block)

    abstract fun createViewModel(): T
}