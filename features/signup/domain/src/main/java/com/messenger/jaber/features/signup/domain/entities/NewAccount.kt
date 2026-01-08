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