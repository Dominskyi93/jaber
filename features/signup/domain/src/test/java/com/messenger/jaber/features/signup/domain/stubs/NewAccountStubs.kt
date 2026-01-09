package com.messenger.jaber.features.signup.domain.stubs

import com.messenger.jaber.features.signup.domain.entities.NewAccount

fun createNewAccount(
    login: String = "test-login",
    password: String = "12345678",
    repeatPassword: String = "12345678",
    firstName: String = "John",
    lastName: String = "Smith",
    age: Int = 25
) = NewAccount.Default(
    login = login,
    password = password,
    repeatPassword = repeatPassword,
    firstName = firstName,
    lastName = lastName,
    age = age
)