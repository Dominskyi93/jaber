package com.messenger.common.android

import android.content.Context
import android.widget.Toast
import com.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidExceptionHandler @Inject constructor(
    private val exceptionToMessageMapper: ExceptionToMessageMapper,
    @ApplicationContext private val context: Context
) : ExceptionHandler {
    override fun handleException(exception: Exception) {
        val message = exceptionToMessageMapper.getLocalizedMessage(exception)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }
}