package com.messenger.jaber.data.messages.di

import com.messenger.jaber.data.MessagesDataRepository
import com.messenger.jaber.data.messages.MessagesDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MessagesDataRepositoryModule {

    @Binds
    fun bindMessagesDataRepository(
        impl: MessagesDataRepositoryImpl
    ): MessagesDataRepository

}