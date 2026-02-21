package com.messenger.jaber.glue.chats

import com.messenger.jaber.data.GetUserInfosDataRepository
import com.messenger.jaber.feature.chats.domain.entities.UserInfo
import com.messenger.jaber.feature.chats.domain.repositories.UserInfoRepository
import com.messenger.jaber.glue.chats.mappers.UserInfoMapper
import javax.inject.Inject

class ChatsGetUserInfoRepository @Inject constructor(
    private val getUserInfosDataRepository: GetUserInfosDataRepository,
    private val userInfoMapper: UserInfoMapper
) : UserInfoRepository {
    override suspend fun findUsersByEmail(email: String): UserInfo?{
        return userInfoMapper.toFeatureEntity(getUserInfosDataRepository.findUsersByEmail(email))
    }
}