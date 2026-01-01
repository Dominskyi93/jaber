package com.messenger.jaber.data.versioning

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.jaber.data.R
import com.messenger.jaber.data.VersioningRepository
import com.messenger.jaber.data.versioning.entities.KeyFeatureDataEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class VersioningRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : VersioningRepository {

    override suspend fun getKeyFeatures(): List<KeyFeatureDataEntity> = Id.idGenerator {
        listOf(
            KeyFeatureDataEntity(
                id = generateId(),
                title = "Security Configuration Audit",
                description = "Automated assessment of critical Android security settings, including screen lock protection, device encryption, bootloader status, security patch level, SELinux mode, and USB debugging. Identifies misconfigurations that increase the risk of device compromise.",
                image = ImageSource.Resource(R.drawable.ic_feature_1)
            ),
            KeyFeatureDataEntity(
                id = generateId(),
                title = "Application Risk Analysis",
                description = "Analyzes installed applications to detect prohibited, sideloaded, or potentially dangerous apps, as well as applications with excessive or sensitive permissions. Provides clear explanations of why specific apps pose a security risk.",
                image = ImageSource.Resource(R.drawable.ic_feature_2)
            ),
            KeyFeatureDataEntity(
                id = generateId(),
                title = "Compliance Report & PDF Export",
                description = "Generates a structured PDF report with audit results, risk levels, issue severity, technical details, and remediation recommendations. Designed for internal security reviews, compliance checks, and official documentation.",
                image = ImageSource.Resource(R.drawable.ic_feature_3)
            )
        )
    }

    override suspend fun getDisplayPeriod(): Period {
        return Period.ofDays(21)
    }

    override suspend fun saveLastDisplayTime(
        keyFeatureId: Id,
        time: ZonedDateTime
    ) {
        dataStore.edit { preferences ->
            preferences[key(keyFeatureId)] = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(time)
        }
    }

    override suspend fun getLastDisplayTime(keyFeatureId: Id): ZonedDateTime {
        return dataStore.data.map { preferences ->
            preferences[key(keyFeatureId)]
                ?.let {
                    ZonedDateTime.parse(it)
                } ?: ZonedDateTime.of(LocalDateTime.MIN, ZoneOffset.UTC)

        }.first()
    }

    private fun key(id: Id): Preferences.Key<String> {
        return stringPreferencesKey("key-feature-display-time-$id")
    }
}