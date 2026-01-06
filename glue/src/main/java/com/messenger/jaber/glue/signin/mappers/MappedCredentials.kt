package com.messenger.jaber.glue.signin.mappers

import com.messenger.jaber.data.accounts.entities.AuthDataCredentials
import com.messenger.jaber.signin.domain.entities.Credentials

internal data class MappedCredentials(
    val origin: Credentials
) : AuthDataCredentials, Credentials by origin