package com.messenger.core.essentials.exceptions

import com.messenger.core.essentials.exceptions.base.AbstractCoreAppException
import com.messenger.core.essentials.resources.CoreStringProvider

class BackendExceptionAbstractCore(
    val httpCode: Int = 400,
    val backendMessage: String = "",
    cause: Throwable? = null
) : AbstractCoreAppException("Server error", cause) {
    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String {
        return stringProvider.backendErrorMessage(httpCode, backendMessage)
    }
}