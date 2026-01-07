package com.messenger.jaber.data.session.entities

sealed class AuthDataResponse {
    data object Success : AuthDataResponse()

    data class Failure(
        val error: Exception
    ) : AuthDataResponse()
}