package com.messenger.jaber.signin.domain.exceptions

import com.messenger.core.essentials.exceptions.base.AbstractAppException
import com.messenger.core.essentials.exceptions.base.WithLocalizedMessage
import com.messenger.core.essentials.resources.StringProviderStore
import com.messenger.jaber.signin.domain.resources.SignInStringProvider

abstract class AbstractSignInAppException(
    message: String,
    cause: Throwable? = null
) : AbstractAppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<SignInStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String
}
