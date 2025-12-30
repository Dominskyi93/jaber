package com.messenger.jaber.core.presentation

import com.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.messenger.core.essentials.logger.Logger
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
    }

    private fun getMixinState() = getMixinState<MixinState>()

    private class MixinState(
        val logger: Logger,
        val exceptionHandler: ExceptionHandler
    )
}