package com.messenger.core.essentials.exceptions

abstract class AppException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)

class UnknownException() : AppException("Unknown exception")

class ConnectionException(
    cause: Throwable? = null
) : AppException("Network exception", cause)

class BackendException(
    val httpCode: Int = 400,
    val backendMessage: String = "",
    cause: Throwable? = null
) : AppException("Server error", cause)
