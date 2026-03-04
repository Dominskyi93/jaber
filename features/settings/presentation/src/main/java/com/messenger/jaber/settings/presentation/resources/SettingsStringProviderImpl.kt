package com.messenger.jaber.settings.presentation.resources

import android.content.Context
import com.messenger.jaber.feature.settings.presentation.R
import com.messenger.jaber.settings.domain.resources.SettingsStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : SettingsStringProvider {
    override val confirmLogoutDialogTitle: String = context.getString(R.string.logout_dialog)
    override val confirmLogoutDialogMessage: String =
        context.getString(R.string.are_you_sure_you_want_to_logout)
}