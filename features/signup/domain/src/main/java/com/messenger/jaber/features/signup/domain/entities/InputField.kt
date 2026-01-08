package com.messenger.jaber.features.signup.domain.entities

import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

sealed interface InputField {

    val fieldName: SignUpStringProvider.() -> String

//    fun validate(value: T): ValidationResult

    sealed class Text(
        override val fieldName: SignUpStringProvider.() -> String,
        val minChars: Int,
        val maxChars: Int
    ) : InputField

    sealed class Number(
        override val fieldName: SignUpStringProvider.() -> String,
        val range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE
    ) : InputField

    data object Login : Text(
        fieldName = { "Login" },
        minChars = 4,
        maxChars = 16
    )

    data object Password : Text(
        fieldName = { "Password" },
        minChars = 6,
        maxChars = 24
    )

    data object RepeatPassword : Text(
        fieldName = { "Repeat Password" },
        minChars = MIN_PASSWORD_LENGTH,
        maxChars = MAX_PASSWORD_LENGTH
    )

    data object FirstName : Text(
        fieldName = { "First Name" },
        minChars = 2,
        maxChars = 32
    )

    data object LastName : Text(
        fieldName = { "Last Name" },
        minChars = 4,
        maxChars = 32
    )

    data object Age : Number(
        fieldName = { "Age" },
        range = 18..150
    )

}

private const val MIN_PASSWORD_LENGTH = 6
private const val MAX_PASSWORD_LENGTH = 24