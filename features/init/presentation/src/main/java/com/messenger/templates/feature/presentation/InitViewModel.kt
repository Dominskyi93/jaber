package com.messenger.templates.feature.presentation

import androidx.lifecycle.ViewModel
import com.messenger.templates.domain.entities.KeyFeature

class InitViewModel : ViewModel() {

    data class State(
        val isLoading: Boolean = true,
        val errorMessage: String? = null,
        val keyFeature: KeyFeature? = null
    )
}