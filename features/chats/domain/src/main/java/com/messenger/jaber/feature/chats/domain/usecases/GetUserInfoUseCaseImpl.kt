package com.messenger.jaber.feature.chats.domain.usecases

import com.messenger.jaber.feature.chats.domain.GetUserInfoUseCase
import com.messenger.jaber.feature.chats.domain.entities.UserInfo
import com.messenger.jaber.feature.chats.domain.repositories.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val repository: UserInfoRepository
) : GetUserInfoUseCase {
    override suspend fun invoke(email: String): UserInfo? {
        return repository.findUsersByEmail(email)
    }
}