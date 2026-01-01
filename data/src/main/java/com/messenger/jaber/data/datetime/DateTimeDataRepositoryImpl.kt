package com.messenger.jaber.data.datetime

import com.messenger.jaber.data.DateTimeDataRepository
import java.time.ZonedDateTime
import javax.inject.Inject

class DateTimeDataRepositoryImpl @Inject constructor(

) : DateTimeDataRepository {
    override fun now(): ZonedDateTime {
        return ZonedDateTime.now()
    }
}