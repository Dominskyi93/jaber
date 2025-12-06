package com.messenger.templates.domain

import com.messenger.core.essentials.exceptions.AppException

class DeviceIsRootedException() : AppException("Device is rooted")