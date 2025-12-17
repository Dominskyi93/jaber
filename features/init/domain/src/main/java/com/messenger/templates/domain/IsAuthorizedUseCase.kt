package com.messenger.templates.domain

import com.messenger.core.essentials.exceptions.ConnectionException
import kotlinx.coroutines.delay
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor() {
    suspend fun invoke(): Boolean {
        delay(2000)
        throw ConnectionException()
    }
}