package com.messenger.jaber.domain.usecases

import com.messenger.jaber.domain.ShowRandomKeyFeatureUseCase
import com.messenger.jaber.domain.entities.KeyFeature
import com.messenger.jaber.domain.entities.ShowKeyFeatureResult
import com.messenger.jaber.domain.repositories.DateTimeRepository
import com.messenger.jaber.domain.repositories.KeyFeaturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowRandomKeyFeatureUseCaseImpl @Inject constructor(
    private val keyFeaturesRepository: KeyFeaturesRepository,
    private val dateTimeRepository: DateTimeRepository
) : ShowRandomKeyFeatureUseCase {
    override fun invoke(): Flow<ShowKeyFeatureResult> = flow {
        if (shouldShowKeyFeature()) {
            val keyFeature = getRandomKeyFeature()
            emit(ShowKeyFeatureResult.Show(keyFeature))
            saveDisplayTime(keyFeature)
        } else {
            emit(ShowKeyFeatureResult.Skip)
        }
    }

    private suspend fun shouldShowKeyFeature(): Boolean {
        val period = keyFeaturesRepository.getDisplayPeriod()
        val lastDisplayTime = keyFeaturesRepository.getKeyFeatures().maxOf {
            it.lastDisplayTime
        }
        val now = dateTimeRepository.now()
        return lastDisplayTime.plus(period) < now
    }

    private suspend fun getRandomKeyFeature(): KeyFeature {
        val keyFeatures = keyFeaturesRepository.getKeyFeatures()
        return keyFeatures.minBy { keyFeature ->
            keyFeature.lastDisplayTime
        }
    }

    private suspend fun saveDisplayTime(keyFeature: KeyFeature) {
        val now = dateTimeRepository.now()
        keyFeaturesRepository.saveDisplayTime(keyFeature, now)
    }
}