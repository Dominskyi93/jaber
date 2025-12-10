package com.messenger.templates.domain.resources

import com.messenger.core.essentials.resources.StringProvider

interface InitStringProvider : StringProvider {
    val deviceIsRootedErrorMessage: String
}