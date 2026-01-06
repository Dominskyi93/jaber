package com.messenger.jaber.core.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
@PublishedApi
internal data class ErrorDto(
    val errcode: String?,
    val error: String?
)