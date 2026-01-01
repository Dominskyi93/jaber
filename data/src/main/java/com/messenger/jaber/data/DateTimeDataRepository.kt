package com.messenger.jaber.data

import java.time.ZonedDateTime

interface DateTimeDataRepository {

    fun now(): ZonedDateTime
}