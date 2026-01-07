package com.messenger.jaber.data.firebaseAuth.di

import com.messenger.jaber.data.FirebaseAuthDataRepository
import com.messenger.jaber.data.firebaseAuth.FirebaseAuthDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseAuthModule {

    @Binds
    fun bindFirebaseAuthRepository(
        impl: FirebaseAuthDataRepositoryImpl
    ): FirebaseAuthDataRepository

}