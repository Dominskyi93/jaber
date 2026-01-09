package com.messenger.jaber.features.signup.domain.entities

interface NewAccount {
    val login: String
    val password: String
    val repeatPassword: String
    val firstName: String
    val lastName: String
    val age: Int

    data class Default(
        override val age: Int,
        override val login: String,
        override val password: String,
        override val repeatPassword: String,
        override val firstName: String,
        override val lastName: String
    ) : NewAccount
}

fun NewAccount.toFieldValues(): List<InputFieldValue<*>> {
    return listOf(
        InputFieldValue(InputField.Login, login),
        InputFieldValue(InputField.Password, password),
        InputFieldValue(InputField.RepeatPassword, repeatPassword),
        InputFieldValue(InputField.FirstName, firstName),
        InputFieldValue(InputField.LastName, lastName),
        InputFieldValue(InputField.Age, age)
    )
}