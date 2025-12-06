package com.messenger.common.android

import android.content.Context
import com.messenger.core.common.android.R
import com.messenger.core.essentials.resources.CoreStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoreStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CoreStringProvider {

    override val connectionErrorMessage = context.getString(R.string.connection_error_message)
    override val unknownErrorMessage: String = context.getString(R.string.unknown_error_message)

    override fun backendErrorMessage(
        code: Int,
        backendMessage: String
    ) = context.getString(R.string.backend_error_message, code, backendMessage)

}