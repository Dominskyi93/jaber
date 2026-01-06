package com.messenger.jaber.data.accounts.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    val user: String,
    val password: String,
    val type: String = "m.login.password",
)