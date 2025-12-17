package com.messenger.core.essentials.exceptions

import com.messenger.core.essentials.exceptions.base.AbstractCoreAppException
import com.messenger.core.essentials.resources.CoreStringProvider

class ConnectionException(
    cause: Throwable? = null
) : AbstractCoreAppException("Network exception", cause) {

    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String {
        return stringProvider.connectionErrorMessage
    }
}