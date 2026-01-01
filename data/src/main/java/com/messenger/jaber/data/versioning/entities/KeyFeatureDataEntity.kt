package com.messenger.jaber.data.versioning.entities

import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource

data class KeyFeatureDataEntity(
    val id: Id,
    val title: String,
    val description: String,
    val image: ImageSource
)