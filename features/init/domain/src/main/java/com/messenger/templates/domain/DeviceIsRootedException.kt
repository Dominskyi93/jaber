package com.messenger.templates.domain

import com.messenger.core.essentials.exceptions.AppException
import com.messenger.core.essentials.exceptions.StringProviderStore
import com.messenger.core.essentials.exceptions.WithLocalizedMessage

class DeviceIsRootedException() : InitAppException("Device is rooted") {
    override fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String {
        return stringProvider.deviceIsRootedErrorMessage
    }
}

abstract class InitAppException(
    message: String,
    cause: Throwable? = null
) : AppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<InitStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String
}