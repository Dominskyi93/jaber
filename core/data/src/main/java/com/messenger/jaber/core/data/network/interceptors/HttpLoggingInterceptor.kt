package com.messenger.jaber.core.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

internal fun createHttpLoggingInterceptor(
    isDebug: Boolean
): Interceptor {
    val level = if (isDebug) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.BODY
    }
    return HttpLoggingInterceptor().setLevel(level)
}