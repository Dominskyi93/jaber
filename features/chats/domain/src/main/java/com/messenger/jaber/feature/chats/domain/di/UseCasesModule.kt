package com.messenger.jaber.feature.chats.domain.di

import com.messenger.jaber.feature.chats.domain.DeleteChatUseCase
import com.messenger.jaber.feature.chats.domain.GetChatsUseCase
import com.messenger.jaber.feature.chats.domain.usecases.DeleteChatUseCaseImpl
import com.messenger.jaber.feature.chats.domain.usecases.GetChatsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCasesModule {

    @Binds
    fun bindGetChatsUseCase(
        impl: GetChatsUseCaseImpl
    ): GetChatsUseCase

    @Binds
    fun bindDeleteChatUseCase(
        impl: DeleteChatUseCaseImpl
    ): DeleteChatUseCase
}