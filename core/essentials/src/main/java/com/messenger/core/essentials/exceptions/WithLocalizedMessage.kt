package com.messenger.core.essentials.exceptions

import com.messenger.core.essentials.resources.StringProvider

interface WithLocalizedMessage<T : StringProvider> {
    fun getLocalizedErrorMessage(stringProvider: T): String
}