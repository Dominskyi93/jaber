package com.messenger.jaber.glue.chats.di

import com.messenger.jaber.feature.chats.domain.repositories.ChatsRepository
import com.messenger.jaber.feature.chats.domain.repositories.UserInfoRepository
import com.messenger.jaber.glue.chats.ChatsGetChatsRepository
import com.messenger.jaber.glue.chats.ChatsGetUserInfoRepository
import com.messenger.jaber.glue.chats.mappers.UserInfoMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ChatsModule {

    @Binds
    fun bindChatsRepository(
        impl: ChatsGetChatsRepository
    ): ChatsRepository

    @Binds
    fun bindGetUserInfoRepository(
        impl: ChatsGetUserInfoRepository
    ): UserInfoRepository

    @Binds
    fun bindUserInfoMapper(
        impl: UserInfoMapper.Default
    ): UserInfoMapper

}