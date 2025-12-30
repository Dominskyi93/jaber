package com.messenger.jaber.core.presentation

import com.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.messenger.core.essentials.logger.Logger
import com.messenger.jaber.core.presentation.base.ViewModelMixin
import com.messenger.jaber.core.presentation.base.getMixinState
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

interface WithCommonDependencies : ViewModelMixin {
    val logger: Logger get() = getMixinState().logger
    val exceptionHandler: ExceptionHandler get() = getMixinState().exceptionHandler

    @Inject
    fun initDependencies(
        logger: Logger,
        exceptionHandler: ExceptionHandler
    ) {
        getMixinState {
            MixinState(logger, exceptionHandler)
        }
        with(getAwaitDependenciesState()) {
            completableDeferred.complete(Unit)
        }
    }

    suspend fun awaitDependencies() = with(getAwaitDependenciesState()) {
        completableDeferred.await()
    }

    private fun getMixinState() = getMixinState<MixinState>()

    private fun getAwaitDependenciesState() = getMixinState(::AwaitDependenciesState)

    private class MixinState(
        val logger: Logger,
        val exceptionHandler: ExceptionHandler
    )

    private class AwaitDependenciesState() {
        val completableDeferred: CompletableDeferred<Unit> = CompletableDeferred()
    }
}