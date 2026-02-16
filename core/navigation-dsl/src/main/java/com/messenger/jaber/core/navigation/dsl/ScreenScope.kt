package com.messenger.jaber.core.navigation.dsl

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

interface ScreenScope : ConfiguredScreen {
    val context: Context
    val coroutineScope: CoroutineScope

    override var toolbar: ScreenToolbar

    override var navigationBar: ScreenNavigationBar

    fun content(block: @Composable () -> Unit)

    fun <T : ViewModel> viewmodel(vmClass: KClass<T>): T

    fun <T : ViewModel, F> viewmodel(vmClass: KClass<T>, callback: F.() -> T): T
}