package com.messenger.jaber.domain.repositories

import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

//todo use interface
@Singleton
class DateTimeRepository @Inject constructor() {
    fun now(): ZonedDateTime {
        return ZonedDateTime.now()
    }
}