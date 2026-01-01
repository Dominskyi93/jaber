package com.messenger.jaber.data.session

import com.messenger.jaber.data.SessionProvider
import com.messenger.jaber.data.session.entities.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class SessionManagerImpl @Inject constructor(

) : SessionProvider {

    override fun getToken(): Flow<Token> {
        //todo
        return flowOf(Token.Empty)
    }
}