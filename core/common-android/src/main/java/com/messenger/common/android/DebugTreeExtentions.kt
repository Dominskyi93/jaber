package com.messenger.common.android

import timber.log.Timber
import kotlin.reflect.KClass

internal fun Timber.DebugTree.addIgnoredClass(
    vararg classes: KClass<*>
) = apply {
    val fqcnIgnoreField = this::class.java.getDeclaredField("fqcnIgnore")
    fqcnIgnoreField.isAccessible = true
    val originList = fqcnIgnoreField.get(this) as List<String>
    val updatedList = originList + classes.map { it.java.name }
    fqcnIgnoreField.set(this, updatedList)
}