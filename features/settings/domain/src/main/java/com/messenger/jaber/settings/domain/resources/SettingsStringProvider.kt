package com.messenger.jaber.settings.domain.resources

import com.messenger.core.essentials.resources.StringProvider

interface SettingsStringProvider : StringProvider {
    val confirmLogoutDialogTitle: String
    val confirmLogoutDialogMessage: String
}