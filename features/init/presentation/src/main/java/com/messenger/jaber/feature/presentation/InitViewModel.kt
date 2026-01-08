package com.messenger.jaber.feature.presentation

import com.elveum.container.Container
import com.elveum.container.factory.SubjectFactory
import com.elveum.container.factory.createReloadableFlow
import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.domain.IsAuthorizedUseCase
import com.messenger.jaber.domain.ShowRandomKeyFeatureUseCase
import com.messenger.jaber.domain.entities.KeyFeature
import com.messenger.jaber.domain.entities.ShowKeyFeatureResult
import com.messenger.jaber.feature.presentation.InitViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class InitViewModel @Inject constructor(
    showRandomKeyFeature: ShowRandomKeyFeatureUseCase,
    private val router: InitRouter,
    private val isAuthorizedUseCase: IsAuthorizedUseCase
) : AbstractViewModel(), WithMviState<State> {

    private val keyFeatureFlow: Flow<Container<KeyFeature>> =
        SubjectFactory.createReloadableFlow {
            showRandomKeyFeature().collect { result ->
                when (result) {
                    is ShowKeyFeatureResult.Show -> emit(result.keyFeature)
                    ShowKeyFeatureResult.Skip -> authorize()
                }
            }
        }

    val stateFlow = keyFeatureFlow
        .containerToReducer(::State, State::copy)
        .stateFlow

    fun letsGo() {
        launch(
            hideProgressPolicy = WithMviState.HideProgressPolicy.ON_ERROR,
            action = ::authorize
        )
    }

    private suspend fun authorize() {
        val isAuthorized = isAuthorizedUseCase.invoke()
        if (isAuthorized) {
            //todo launch main screen
        } else {
            router.launchSignIn()
        }
    }

    data class State(
        val keyFeature: KeyFeature,
        val isCheckAuthInProgress: Boolean = false
    )
}