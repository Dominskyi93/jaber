package com.messenger.core.essentials.datetime

import java.time.ZonedDateTime

interface DateTimeProvider {

    fun now(): ZonedDateTime

    fun currentTimeMillis(): Long
}