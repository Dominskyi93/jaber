package com.messenger.jaber.domain

import com.messenger.jaber.domain.entities.ShowKeyFeatureResult
import kotlinx.coroutines.flow.Flow

interface ShowRandomKeyFeatureUseCase {
    operator fun invoke(): Flow<ShowKeyFeatureResult>
}

