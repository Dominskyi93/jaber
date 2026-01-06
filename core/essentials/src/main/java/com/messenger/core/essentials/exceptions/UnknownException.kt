package com.messenger.core.essentials.exceptions

import com.messenger.core.essentials.exceptions.base.AbstractAppException

class UnknownException(
    cause: Throwable
) : AbstractAppException("Unknown exception", cause)
