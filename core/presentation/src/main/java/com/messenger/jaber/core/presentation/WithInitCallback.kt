package com.messenger.jaber.core.presentation

import com.messenger.core.essentials.logger.Logger
import com.messenger.jaber.core.presentation.base.ViewModelMixin
import kotlinx.coroutines.launch
import javax.inject.Inject

interface WithInitCallback : ViewModelMixin {

    @Inject
    fun initializeViewModel(
        logger: Logger
    ) {
        logger.d("ViewModel ${this::class.simpleName} is initialized")
        coroutineScope.launch {
            if (this@WithInitCallback is WithCommonDependencies) {
                awaitDependencies()
            }
            onInitialized()
        }
    }

    suspend fun onInitialized()
}