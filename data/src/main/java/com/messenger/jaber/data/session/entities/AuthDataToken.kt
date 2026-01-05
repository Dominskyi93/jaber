package com.messenger.jaber.data.session.entities

sealed class AuthDataToken {

    data object Empty : AuthDataToken()

    data class Default(
        val accessToken: String
    ) : AuthDataToken()

}