package com.messenger.jaber.signin.domain

import com.messenger.core.essentials.Container
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAuthorizedUseCase @Inject constructor() {

    fun invoke(): Flow<Container<Authorize>> = flow {
        emit(Container.Loading)
        delay(2000)
        emit(Container.Success(Authorize()))
    }
}
