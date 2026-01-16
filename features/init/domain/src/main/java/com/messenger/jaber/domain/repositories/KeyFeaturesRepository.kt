package com.messenger.jaber.domain.repositories

import com.messenger.jaber.domain.entities.KeyFeature
import java.time.Period
import java.time.ZonedDateTime

interface KeyFeaturesRepository {
    suspend fun getKeyFeatures(): List<KeyFeature>
    suspend fun getDisplayPeriod(): Period
    suspend fun saveDisplayTime(keyFeature: KeyFeature, time: ZonedDateTime)
}