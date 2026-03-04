package com.messenger.common.android

import android.content.Context
import com.messenger.core.common.android.R
import com.messenger.core.essentials.resources.CoreStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoreStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : CoreStringProvider {

    override val connectionErrorMessage = context.getString(R.string.connection_error_message)
    override val unknownErrorMessage: String = context.getString(R.string.unknown_error_message)
    override val invalidBackendResponseErrorMessage: String =
        context.getString(R.string.invalid_response_from_the_remote_server)
    override val delete: String = context.getString(R.string.delete)
    override val cancel: String = context.getString(R.string.cancel)
    override val yes: String = context.getString(R.string.yes)

    override fun backendErrorMessage(
        code: Int,
        backendMessage: String
    ) = context.getString(R.string.backend_error_message, code, backendMessage)

}