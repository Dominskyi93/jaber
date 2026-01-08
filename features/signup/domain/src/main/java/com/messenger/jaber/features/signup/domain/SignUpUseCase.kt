package com.messenger.jaber.features.signup.domain

import com.messenger.jaber.features.signup.domain.entities.NewAccount

interface SignUpUseCase {

    suspend operator fun invoke(account: NewAccount)
}