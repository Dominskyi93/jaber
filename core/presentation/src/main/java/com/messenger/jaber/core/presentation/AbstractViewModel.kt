package com.messenger.jaber.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import java.lang.AutoCloseable
import kotlin.Any
import kotlin.reflect.KClass

abstract class AbstractViewModel : ViewModel(), ViewModelMixin {

    private val mixinStateMap = mutableMapOf<KClass<*>, Any>()

    override val coroutineScope: CoroutineScope = viewModelScope


    override fun <T : Any> getMixinState(
        stateClass: KClass<T>,
        initializer: () -> T
    ): T {
        return mixinStateMap.getOrPut(stateClass) {
            initializer()
        } as T
    }

    override fun onCleared() {
        super.onCleared()
        mixinStateMap.values
            .filterIsInstance<AutoCloseable>()
            .forEach(AutoCloseable::close)
        mixinStateMap.clear()
    }
}