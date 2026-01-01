package com.messenger.jaber.glue.init

import com.messenger.jaber.data.VersioningRepository
import com.messenger.jaber.domain.entities.KeyFeature
import com.messenger.jaber.domain.repositories.KeyFeaturesRepository
import com.messenger.jaber.glue.init.mappers.KeyFeatureMapper
import java.time.Period
import java.time.ZonedDateTime
import javax.inject.Inject

class InitKeyFeaturesRepository @Inject constructor(
    private val versioningRepository: VersioningRepository,
    private val keyFeatureMapper: KeyFeatureMapper
) : KeyFeaturesRepository {
    override suspend fun getKeyFeatures(): List<KeyFeature> {
        return versioningRepository.getKeyFeatures().map {
            keyFeatureMapper.toFeatureEntity(it)
        }
    }

    override suspend fun getDisplayPeriod(): Period {
        return versioningRepository.getDisplayPeriod()
    }

    override suspend fun saveDisplayTime(
        keyFeature: KeyFeature,
        time: ZonedDateTime
    ) {
        versioningRepository.saveLastDisplayTime(
            keyFeatureId = keyFeature.id,
            time = time
        )
    }
}