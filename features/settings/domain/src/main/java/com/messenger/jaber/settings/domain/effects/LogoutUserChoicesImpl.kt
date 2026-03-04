package com.messenger.jaber.settings.domain.effects

import com.messenger.core.essentials.dialogs.Dialogs
import com.messenger.core.essentials.dialogs.DialogsConfig
import com.messenger.core.essentials.logger.DefaultLogger
import com.messenger.core.essentials.resources.CoreStringProvider
import com.messenger.jaber.settings.domain.resources.SettingsStringProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LogoutUserChoicesImpl @Inject constructor(
    private val dialogs: Dialogs,
    private val settingsStringProvider: SettingsStringProvider,
    private val coreStringProvider: CoreStringProvider
) : LogoutUserChoices {
    override suspend fun confirmLogout(): Boolean {
        DefaultLogger.d("confirmLogout")
        val config = DialogsConfig.Default(
            title = settingsStringProvider.confirmLogoutDialogTitle,
            message = settingsStringProvider.confirmLogoutDialogMessage,
            positiveButton = coreStringProvider.yes,
            negativeButton = coreStringProvider.cancel
        )
        return dialogs.showAlertDialog(config)
    }
}