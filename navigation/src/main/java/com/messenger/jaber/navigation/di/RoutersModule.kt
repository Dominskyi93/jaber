package com.messenger.jaber.navigation.di

import com.messenger.jaber.feature.presentation.InitRouter
import com.messenger.jaber.navigation.routers.InitRouterImpl
import com.messenger.jaber.navigation.routers.SignInRouterImpl
import com.messenger.jaber.signin.presentation.SignInRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RoutersModule {

    @Binds
    fun bindInitRouter(
        impl: InitRouterImpl
    ): InitRouter

    @Binds
    fun bindSignInRouter(
        impl: SignInRouterImpl
    ): SignInRouter
}