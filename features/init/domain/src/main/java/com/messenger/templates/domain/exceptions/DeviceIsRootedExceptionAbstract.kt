package com.messenger.templates.domain.exceptions

import com.messenger.core.essentials.exceptions.base.AbstractAppException
import com.messenger.core.essentials.resources.StringProviderStore
import com.messenger.core.essentials.exceptions.base.WithLocalizedMessage
import com.messenger.templates.domain.resources.InitStringProvider

class DeviceIsRootedExceptionAbstract() : InitAbstractAppException("Device is rooted") {
    override fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String {
        return stringProvider.deviceIsRootedErrorMessage
    }
}

abstract class InitAbstractAppException(
    message: String,
    cause: Throwable? = null
) : AbstractAppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<InitStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String
}