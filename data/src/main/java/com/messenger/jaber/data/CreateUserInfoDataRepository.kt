package com.messenger.jaber.data

import com.messenger.jaber.data.firestore.userInfo.entities.UserInfo

interface CreateUserInfoDataRepository {
    suspend fun createUserInfo(userInfo: UserInfo.Default)
}