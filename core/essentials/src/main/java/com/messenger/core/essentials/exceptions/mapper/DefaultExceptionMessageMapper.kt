package com.messenger.core.essentials.exceptions.mapper

import com.messenger.core.essentials.resources.StringProviderStore
import com.messenger.core.essentials.exceptions.base.WithLocalizedMessage
import com.messenger.core.essentials.resources.CoreStringProvider
import javax.inject.Inject

class DefaultExceptionMessageMapper @Inject constructor(
    private val stringProviderStore: StringProviderStore
) : ExceptionToMessageMapper {
    override fun getLocalizedMessage(exception: Exception): String {
        return if (exception is WithLocalizedMessage) {
            exception.getLocalizedErrorMessage(stringProviderStore)
        } else {
            stringProviderStore<CoreStringProvider>().unknownErrorMessage
        }
    }
}