package com.messenger.jaber.glue.signup

import com.messenger.jaber.data.LoginAvailabilityDataRepository
import com.messenger.jaber.features.signup.domain.repositories.LoginAvailabilityRepository
import javax.inject.Inject

class SignUpLoginAvailabilityRepository @Inject constructor(
    private val loginAvailabilityDataRepository: LoginAvailabilityDataRepository
) : LoginAvailabilityRepository {
    override suspend fun isLoginAvailable(login: String): Boolean {
        return loginAvailabilityDataRepository.isLoginAvailable()
    }

}