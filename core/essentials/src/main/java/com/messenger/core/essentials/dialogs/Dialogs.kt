package com.messenger.core.essentials.dialogs

interface Dialogs {

    suspend fun showAlertDialog(config: DialogsConfig): Boolean
}