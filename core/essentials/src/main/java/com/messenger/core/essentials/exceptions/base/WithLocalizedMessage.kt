package com.messenger.core.essentials.exceptions.base

import com.messenger.core.essentials.resources.StringProviderStore

interface WithLocalizedMessage {
    fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String
}