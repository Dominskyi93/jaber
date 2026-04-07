package com.messenger.jaber.feature.chat_room.domain.di

import com.messenger.jaber.feature.chat_room.domain.GetMessagesUseCase
import com.messenger.jaber.feature.chat_room.domain.SaveMessageUseCase
import com.messenger.jaber.feature.chat_room.domain.usecases.GetMessagesUseCaseImpl
import com.messenger.jaber.feature.chat_room.domain.usecases.SaveMessageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCasesModule {

    @Binds
    fun bindGetMessagesUseCase(
        impl: GetMessagesUseCaseImpl
    ): GetMessagesUseCase

    @Binds
    fun bindSaveMessageUseCase(
        impl: SaveMessageUseCaseImpl
    ): SaveMessageUseCase

}