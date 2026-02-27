package com.messenger.jaber.data.rooms.di

import com.messenger.jaber.data.CreateChatDataRepository
import com.messenger.jaber.data.RoomsDataRepository
import com.messenger.jaber.data.rooms.CreateChatDataRepositoryImpl
import com.messenger.jaber.data.rooms.RoomsDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RoomsRepositoryModule {

    @Binds
    fun bindRoomsRepository(
        impl: RoomsDataRepositoryImpl
    ): RoomsDataRepository

    @Binds
    fun bindCreateChatRepository(
        impl: CreateChatDataRepositoryImpl
    ): CreateChatDataRepository

}