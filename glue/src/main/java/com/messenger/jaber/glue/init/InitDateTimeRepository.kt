package com.messenger.jaber.glue.init

import com.messenger.jaber.data.DateTimeDataRepository
import com.messenger.jaber.domain.repositories.DateTimeRepository
import javax.inject.Inject

class InitDateTimeRepository @Inject constructor(
    private val dateTimeDataRepository: DateTimeDataRepository
) : DateTimeRepository, DateTimeDataRepository by dateTimeDataRepository