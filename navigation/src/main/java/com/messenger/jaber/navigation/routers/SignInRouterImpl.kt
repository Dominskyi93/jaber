package com.messenger.jaber.navigation.routers

import android.content.Context
import android.widget.Toast
import com.messenger.jaber.navigation.base.AppRouter
import com.messenger.jaber.signin.presentation.SignInRouter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignInRouterImpl @Inject constructor(
    private val appRouter: AppRouter,
    @ApplicationContext private val context: Context
) : SignInRouter {
    override fun launchTermsAndConditions() {
        Toast.makeText(context, "launchTermsAndConditions", Toast.LENGTH_SHORT).show()
    }

    override fun launchPrivacyPolicy() {
        Toast.makeText(context, "launchPrivacyPolicy", Toast.LENGTH_SHORT).show()
    }

    override fun navigateUp() {
        appRouter.goBack()
    }

    override fun launchMain() {
        Toast.makeText(context, "launchMain", Toast.LENGTH_SHORT).show()
    }
}