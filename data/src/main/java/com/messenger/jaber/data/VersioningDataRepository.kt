package com.messenger.jaber.data

import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.data.versioning.entities.KeyFeatureDataEntity
import java.time.Period
import java.time.ZonedDateTime

interface VersioningDataRepository {

    suspend fun getKeyFeatures(): List<KeyFeatureDataEntity>

    suspend fun getDisplayPeriod(): Period

    suspend fun saveLastDisplayTime(keyFeatureId: Id, time: ZonedDateTime)

    suspend fun getLastDisplayTime(keyFeatureId: Id): ZonedDateTime
}