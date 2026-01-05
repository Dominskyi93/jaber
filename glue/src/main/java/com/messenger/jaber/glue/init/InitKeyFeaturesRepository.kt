package com.messenger.jaber.glue.init

import com.messenger.jaber.data.VersioningDataRepository
import com.messenger.jaber.domain.entities.KeyFeature
import com.messenger.jaber.domain.repositories.KeyFeaturesRepository
import com.messenger.jaber.glue.init.mappers.KeyFeatureMapper
import java.time.Period
import java.time.ZonedDateTime
import javax.inject.Inject

class InitKeyFeaturesRepository @Inject constructor(
    private val versioningDataRepository: VersioningDataRepository,
    private val keyFeatureMapper: KeyFeatureMapper
) : KeyFeaturesRepository {
    override suspend fun getKeyFeatures(): List<KeyFeature> {
        return versioningDataRepository.getKeyFeatures().map {
            keyFeatureMapper.toFeatureEntity(it)
        }
    }

    override suspend fun getDisplayPeriod(): Period {
        return versioningDataRepository.getDisplayPeriod()
    }

    override suspend fun saveDisplayTime(
        keyFeature: KeyFeature,
        time: ZonedDateTime
    ) {
        versioningDataRepository.saveLastDisplayTime(
            keyFeatureId = keyFeature.id,
            time = time
        )
    }
}