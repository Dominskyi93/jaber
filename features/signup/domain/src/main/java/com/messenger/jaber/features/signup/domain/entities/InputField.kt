package com.messenger.jaber.features.signup.domain.entities

import com.messenger.jaber.features.signup.domain.exceptions.EmptyFieldException
import com.messenger.jaber.features.signup.domain.exceptions.InvalidRangeException
import com.messenger.jaber.features.signup.domain.exceptions.TooLongValueException
import com.messenger.jaber.features.signup.domain.exceptions.TooShortValueException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

sealed interface InputField<T> {

    val fieldName: SignUpStringProvider.() -> String

    fun validate(value: T): ValidationResult

    sealed class Text(
        override val fieldName: SignUpStringProvider.() -> String,
        val minChars: Int,
        val maxChars: Int
    ) : InputField<String> {
        override fun validate(value: String): ValidationResult {
            val trimmedValue = value.trim()
            return when {
                trimmedValue.isEmpty() -> {
                    ValidationResult.Failure(EmptyFieldException(this))
                }
                trimmedValue.length < minChars -> {
                    ValidationResult.Failure(TooShortValueException(this, trimmedValue))
                }
                trimmedValue.length > maxChars -> {
                    ValidationResult.Failure(TooLongValueException(this, trimmedValue))
                }
                else -> {
                    ValidationResult.Success
                }
            }
        }
    }

    sealed class Number(
        override val fieldName: SignUpStringProvider.() -> String,
        val range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE
    ) : InputField<Int> {
        override fun validate(value: Int): ValidationResult {
            return if (value in range) {
                ValidationResult.Success
            } else {
                ValidationResult.Failure(InvalidRangeException(this, "$value"))
            }
        }
    }

    data object Login : Text(
        fieldName = { loginField },
        minChars = 4,
        maxChars = 16
    )

    data object Password : Text(
        fieldName = { passwordField },
        minChars = 6,
        maxChars = 24
    )

    data object RepeatPassword : Text(
        fieldName = { repeatPasswordField },
        minChars = MIN_PASSWORD_LENGTH,
        maxChars = MAX_PASSWORD_LENGTH
    )

    data object FirstName : Text(
        fieldName = { firstNameField },
        minChars = 2,
        maxChars = 32
    )

    data object LastName : Text(
        fieldName = { lastNameField },
        minChars = 4,
        maxChars = 32
    )

    data object Age : Number(
        fieldName = { ageField },
        range = 18..150
    )

}

private const val MIN_PASSWORD_LENGTH = 6
private const val MAX_PASSWORD_LENGTH = 24