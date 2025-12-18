package com.messenger.jaber.domain

import kotlinx.coroutines.delay
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor() {
    suspend fun invoke(): Boolean {
        delay(2000)
        return false
    }
}