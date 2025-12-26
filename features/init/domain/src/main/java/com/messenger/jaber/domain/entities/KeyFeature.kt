package com.messenger.jaber.domain.entities

import com.messenger.core.essentials.entities.ImageSource

data class KeyFeature(
    val id: Long,
    val title: String,
    val description: String,
    val image: ImageSource
)
