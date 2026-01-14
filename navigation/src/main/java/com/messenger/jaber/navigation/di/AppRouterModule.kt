package com.messenger.jaber.navigation.di

import com.messenger.jaber.navigation.base.AppRouter
import com.messenger.jaber.navigation.base.impl.NavComponentAppRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AppRouterModule {

    @Binds
    fun bindAppRouter(
        impl: NavComponentAppRouter
    ): AppRouter
}