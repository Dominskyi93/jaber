package com.messenger.jaber.core.data.network.converter

import kotlinx.serialization.json.Json

@PublishedApi
internal val errorJson = Json {
    explicitNulls = false
    ignoreUnknownKeys = true
}
