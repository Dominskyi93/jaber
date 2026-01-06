package com.messenger.jaber.glue.signin.mappers

import com.messenger.jaber.data.session.entities.AuthDataToken
import com.messenger.jaber.signin.domain.entities.Token

internal data class MappedToken(
    val origin: AuthDataToken.Default
) : Token
