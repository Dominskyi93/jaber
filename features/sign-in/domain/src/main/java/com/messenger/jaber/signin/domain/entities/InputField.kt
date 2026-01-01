package com.messenger.jaber.signin.domain.entities

import com.messenger.jaber.signin.domain.resources.SignInStringProvider

enum class InputField(val fieldName: SignInStringProvider.() -> String) {
    LOGIN(fieldName = { loginFieldName }),
    PASSWORD(fieldName = { passwordFieldName })
}