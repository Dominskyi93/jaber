package com.messenger.core.essentials.resources

interface CoreStringProvider : StringProvider {
    val connectionErrorMessage: String
    val unknownErrorMessage: String
    val invalidBackendResponseErrorMessage: String
    val delete: String
    val cancel: String
    val yes: String

    fun backendErrorMessage(code: Int, backendMessage: String): String
}