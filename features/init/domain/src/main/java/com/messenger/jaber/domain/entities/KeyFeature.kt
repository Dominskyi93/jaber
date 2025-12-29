package com.messenger.jaber.domain.entities

import com.messenger.core.essentials.entities.ImageSource
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class KeyFeature(
    val id: Long,
    val title: String,
    val description: String,
    val image: ImageSource,
    internal val lastDisplayTime: ZonedDateTime = ZonedDateTime.ofInstant(
        Instant.EPOCH,
        ZoneId.systemDefault()
    )
)
