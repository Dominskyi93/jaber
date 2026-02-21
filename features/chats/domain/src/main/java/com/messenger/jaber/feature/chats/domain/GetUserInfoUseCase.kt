package com.messenger.jaber.feature.chats.domain

import com.messenger.jaber.feature.chats.domain.entities.UserInfo

interface GetUserInfoUseCase {
    suspend operator fun invoke(email: String): UserInfo?
}