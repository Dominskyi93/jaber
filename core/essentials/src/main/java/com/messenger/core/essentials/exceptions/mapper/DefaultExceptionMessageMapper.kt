package com.messenger.core.essentials.exceptions.mapper

import com.messenger.core.essentials.exceptions.WithLocalizedMessage
import com.messenger.core.essentials.resources.CoreStringProvider
import javax.inject.Inject

class DefaultExceptionMessageMapper @Inject constructor(
    private val stringProvider: CoreStringProvider
) : ExceptionToMessageMapper {
    override fun getLocalizedMessage(exception: Exception): String {
        return if (exception is WithLocalizedMessage) {
            exception.getLocalizedErrorMessage()
        } else {
            stringProvider.unknownErrorMessage
        }
    }
}