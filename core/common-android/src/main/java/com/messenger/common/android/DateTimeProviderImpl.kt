package com.messenger.common.android

import com.messenger.core.essentials.datetime.DateTimeProvider
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DateTimeProviderImpl @Inject constructor() : DateTimeProvider {
    override fun now(): ZonedDateTime = ZonedDateTime.now()
    override fun currentTimeMillis() = System.currentTimeMillis()
}