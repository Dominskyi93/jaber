package com.messenger.jaber.data

import com.messenger.jaber.data.userInfo.entities.UserInfoResponse

interface CreateUserInfoDataRepository {
    suspend fun createUserInfo(userInfoResponse: UserInfoResponse)
}