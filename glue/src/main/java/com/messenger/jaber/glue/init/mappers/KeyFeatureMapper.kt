package com.messenger.jaber.glue.init.mappers

import com.messenger.jaber.data.VersioningRepository
import com.messenger.jaber.data.versioning.entities.KeyFeatureDataEntity
import com.messenger.jaber.domain.entities.KeyFeature
import javax.inject.Inject

interface KeyFeatureMapper {
    suspend fun toFeatureEntity(
        dataEntity: KeyFeatureDataEntity
    ): KeyFeature

    class Default @Inject constructor(
        private val versioningRepository: VersioningRepository
    ) : KeyFeatureMapper {
        override suspend fun toFeatureEntity(dataEntity: KeyFeatureDataEntity): KeyFeature {
            return KeyFeature(
                id = dataEntity.id,
                title = dataEntity.title,
                description = dataEntity.description,
                image = dataEntity.image,
                lastDisplayTime = versioningRepository.getLastDisplayTime(dataEntity.id)
            )
        }
    }
}