package com.messenger.jaber.features.signup.domain.entities

import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException

sealed interface ValidationResult {

    operator fun plus(exception: AbstractValidationException): ValidationResult

    data object Success : ValidationResult {
        override fun plus(exception: AbstractValidationException): ValidationResult {
            return ValidationResult.Failure(exception)
        }
    }

    data class Failure(
        val exceptions: List<AbstractValidationException>
    ) : ValidationResult {

        constructor(exception: AbstractValidationException) : this(
            listOf(exception)
        )

        override fun plus(exception: AbstractValidationException): ValidationResult {
            return Failure(exceptions = exceptions + exception)
        }
    }

    companion object {
        fun List<ValidationResult>.combined(): ValidationResult {
            val exceptions = this.filterIsInstance<Failure>()
                .flatMap { it.exceptions }
            return if (exceptions.isEmpty()) {
                Success
            } else {
                Failure(exceptions)
            }
        }
    }
}