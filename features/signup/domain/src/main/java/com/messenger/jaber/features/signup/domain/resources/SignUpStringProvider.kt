package com.messenger.jaber.features.signup.domain.resources

import com.messenger.core.essentials.resources.StringProvider

interface SignUpStringProvider : StringProvider {
    val loginFieldName: String
    val passwordFieldName: String
    val invalidCredentialsError: String
}