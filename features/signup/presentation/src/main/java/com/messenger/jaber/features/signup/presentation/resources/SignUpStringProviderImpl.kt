package com.messenger.jaber.features.signup.presentation.resources

import android.content.Context
import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import com.messenger.jaber.features.signup.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignUpStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SignUpStringProvider {
    override val loginField = context.getString(R.string.login)
    override val passwordField = context.getString(R.string.password)
    override val repeatPasswordField = context.getString(R.string.repeat_password)
    override val firstNameField = context.getString(R.string.first_name)
    override val lastNameField = context.getString(R.string.last_name)
    override val ageField = context.getString(R.string.age)

    override val passwordMismatchError =
        context.getString(R.string.passwords_do_not_match)

    override fun loginAlreadyExistsError(email: String): String {
        return context.getString(R.string.signup_an_account_with_the_email_already_exists, email)
    }

    override fun emptyFieldError(field: InputField.Text): String {
        val fieldName = field.fieldName(this)
        return context.getString(R.string.is_empty, fieldName)
    }

    override fun invalidRangeError(field: InputField.Number): String {
        val fieldName = field.fieldName(this)
        return context.getString(
            R.string.must_be_between_and,
            fieldName,
            field.range.first,
            field.range.last
        )
    }

    override fun tooLongValueError(field: InputField.Text): String {
        val fieldName = field.fieldName(this)
        return context.getString(R.string.is_too_long_maximum_characters, fieldName, field.maxChars)
    }

    override fun tooShortValueError(field: InputField.Text): String {
        val fieldName = field.fieldName(this)
        return context.getString(
            R.string.is_too_short_minimum_characters,
            fieldName,
            field.minChars
        )
    }
}