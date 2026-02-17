package com.messenger.jaber.navigation.routers

import com.messenger.jaber.feature.profile.presentation.ProfileRouter
import com.messenger.jaber.navigation.base.AppRouter
import javax.inject.Inject

class ProfileRouterImpl @Inject constructor(
    private val appRouter: AppRouter
) : ProfileRouter {

    override fun navigateUp() {
        appRouter.goBack()
    }
}