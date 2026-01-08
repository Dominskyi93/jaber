package com.messenger.jaber.features.signup.domain.exceptions.base

import com.messenger.core.essentials.exceptions.base.AbstractAppException
import com.messenger.core.essentials.exceptions.base.WithLocalizedMessage
import com.messenger.core.essentials.resources.StringProviderStore
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

abstract class AbstractSignUpAppException(
    message: String,
    cause: Throwable? = null
) : AbstractAppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<SignUpStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String
}