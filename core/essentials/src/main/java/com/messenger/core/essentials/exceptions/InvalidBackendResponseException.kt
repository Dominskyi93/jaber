package com.messenger.core.essentials.exceptions

import com.messenger.core.essentials.exceptions.base.AbstractCoreAppException
import com.messenger.core.essentials.resources.CoreStringProvider

class InvalidBackendResponseException(
    cause: Throwable?
) : AbstractCoreAppException("Can`t parse server response", cause) {
    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String {
        return stringProvider.invalidBackendResponseErrorMessage
    }
}