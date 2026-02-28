package com.messenger.jaber.navigation.routers

import com.messenger.jaber.navigation.base.AppRouter
import com.messenger.jaber.settings.presentation.SettingsRouter
import javax.inject.Inject

class SettingsRouterImpl @Inject constructor(
    private val appRouter: AppRouter
) : SettingsRouter {
    override fun navigateUp() {
        appRouter.goBack()
    }
}