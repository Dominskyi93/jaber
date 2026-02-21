package com.messenger.jaber.feature.chats.domain.repositories

import com.messenger.jaber.feature.chats.domain.entities.UserInfo

interface UserInfoRepository {
    suspend fun findUsersByEmail(email: String): UserInfo?
}