package com.messenger.jaber.data.accounts.exceptions

import com.messenger.core.essentials.exceptions.base.AbstractAppException

class InvalidCredentialsDataException(
    cause: Throwable? = null
) : AbstractAppException("Invalid login and/or password", cause)