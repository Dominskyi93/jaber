package com.messenger.jaber.data.chats.di

import com.messenger.jaber.data.ChatsDataRepository
import com.messenger.jaber.data.chats.ChatsDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatsModule {

    @Binds
    fun bindChatsDataRepository(
        impl: ChatsDataRepositoryImpl
    ): ChatsDataRepository
}