package com.messenger.jaber.signin.domain.entities

sealed class AuthResponse {
    data object Success : AuthResponse()

    data class Failure(
        val error: Exception
    ) : AuthResponse()
}