package com.messenger.jaber.data.accounts.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    val accessToken: String
)