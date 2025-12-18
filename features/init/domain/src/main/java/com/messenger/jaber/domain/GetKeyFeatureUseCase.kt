package com.messenger.jaber.domain

import com.messenger.core.essentials.Container
import com.messenger.jaber.domain.entities.KeyFeature
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetKeyFeatureUseCase @Inject constructor() {

    fun invoke(): Flow<Container<KeyFeature>> = flow {
        emit(Container.Loading)
        delay(2000)
        emit(Container.Success(KeyFeature(0L, "df", "dfdfdfdfd d")))
    }
}

