package com.messenger.core.essentials.exceptions

interface WithLocalizedMessage {
    fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String
}