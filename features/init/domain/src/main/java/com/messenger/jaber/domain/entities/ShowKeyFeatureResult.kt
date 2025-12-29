package com.messenger.jaber.domain.entities

sealed class ShowKeyFeatureResult {
    data class Show(val keyFeature: KeyFeature) : ShowKeyFeatureResult()
    data object Skip : ShowKeyFeatureResult()
}