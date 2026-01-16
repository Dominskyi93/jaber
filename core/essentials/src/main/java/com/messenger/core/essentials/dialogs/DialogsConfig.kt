package com.messenger.core.essentials.dialogs

interface DialogsConfig {
    val title: String
    val message: String
    val positiveButton: String
    val negativeButton: String?

    data class Default(
        override val message: String,
        override val title: String,
        override val positiveButton: String,
        override val negativeButton: String? = null
    ) : DialogsConfig
}