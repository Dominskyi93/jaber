package com.messenger.jaber.features.signup.domain.entities

import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException

sealed interface ValidationResult {

    data object Success : ValidationResult

    data class Failure(
        val exceptions: List<AbstractValidationException>
    ) : ValidationResult
}