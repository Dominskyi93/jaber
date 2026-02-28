package com.messenger.jaber.navigation.di

import com.messenger.jaber.feature.chats.presentation.ChatsRouter
import com.messenger.jaber.feature.presentation.InitRouter
import com.messenger.jaber.features.signup.presentation.SignUpRouter
import com.messenger.jaber.navigation.routers.ChatsRouterImpl
import com.messenger.jaber.navigation.routers.InitRouterImpl
import com.messenger.jaber.navigation.routers.SettingsRouterImpl
import com.messenger.jaber.navigation.routers.SignInRouterImpl
import com.messenger.jaber.navigation.routers.SignUpRouterImpl
import com.messenger.jaber.settings.presentation.SettingsRouter
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

    @Binds
    fun bindSignUpRouter(
        impl: SignUpRouterImpl
    ): SignUpRouter

    @Binds
    fun bindChatsRouter(
        impl: ChatsRouterImpl
    ): ChatsRouter

    @Binds
    fun bindSettingsRouter(
        impl: SettingsRouterImpl
    ): SettingsRouter
}