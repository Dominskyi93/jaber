package com.messenger.jaber.domain.repositories

import java.time.ZonedDateTime

interface DateTimeRepository {
    fun now(): ZonedDateTime
}