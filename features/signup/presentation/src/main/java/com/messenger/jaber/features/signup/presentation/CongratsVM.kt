package com.messenger.jaber.features.signup.presentation

import com.messenger.jaber.core.presentation.base.AbstractViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CongratsVM @Inject constructor(
    private val router: SignUpRouter
) : AbstractViewModel() {

    fun goBackToSignIn() {
        router.goBackToSignIn()
    }
}