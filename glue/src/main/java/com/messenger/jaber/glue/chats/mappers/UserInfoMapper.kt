package com.messenger.jaber.glue.chats.mappers

import com.messenger.jaber.data.userInfo.entities.UserInfoRequest
import com.messenger.jaber.feature.chats.domain.entities.UserInfo
import javax.inject.Inject

interface UserInfoMapper {
    suspend fun toFeatureEntity(
        dataEntity: UserInfoRequest?
    ): UserInfo?

    class Default @Inject constructor() : UserInfoMapper {
        override suspend fun toFeatureEntity(dataEntity: UserInfoRequest?): UserInfo? {
            return if (dataEntity == null) {
                null
            } else {
                UserInfo.Default(
                    avatarUrl = dataEntity.avatarUrl,
                    uid = dataEntity.uid,
                    fcmToken = dataEntity.fcmToken,
                    phone = dataEntity.phone,
                    login = dataEntity.login,
                    firstName = dataEntity.firstName,
                    lastName = dataEntity.lastName,
                    bio = dataEntity.bio,
                    lastSeen = dataEntity.lastSeen,
                    createdAt = dataEntity.createdAt
                )
            }
        }
    }
}