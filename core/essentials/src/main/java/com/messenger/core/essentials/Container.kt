package com.messenger.core.essentials

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class Container<out T> {
    abstract fun <R> map(mapper: (T) -> R): Container<R>

    data object Loading : Container<Nothing>() {
        override fun <R> map(mapper: (Nothing) -> R) = Loading
    }

    data class Error(
        val exception: Exception
    ) : Container<Nothing>() {
        override fun <R> map(mapper: (Nothing) -> R) = this
    }

    data class Success<T>(
        val value: T
    ) : Container<T>() {
        override fun <R> map(mapper: (T) -> R) = Success(mapper(value))
    }
}

fun <T, R> Flow<Container<T>>.containerMap(
    mapper: (T) -> R
): Flow<Container<R>> {
    return map { container ->
        container.map(mapper)
    }
}