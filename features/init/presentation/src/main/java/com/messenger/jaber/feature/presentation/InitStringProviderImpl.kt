package com.messenger.jaber.feature.presentation

import android.content.Context
import com.messenger.jaber.domain.resources.InitStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InitStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : InitStringProvider {
    override val deviceIsRootedErrorMessage: String =
        context.getString(R.string.rooted_device_error)

}