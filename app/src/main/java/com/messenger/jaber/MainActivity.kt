package com.messenger.jaber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.messenger.common.android.AndroidExceptionHandler
import com.messenger.core.theme.material.JaberTheme
import com.messenger.jaber.navigation.AppNavHost
import com.messenger.jaber.navigation.base.impl.ComposeDialogs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var exceptionHandler: AndroidExceptionHandler

    @Inject
    lateinit var dialogs: ComposeDialogs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JaberTheme {
                JaberApp(dialogs)
                exceptionHandler.ErrorDialog()
            }
        }
    }
}

//@PreviewScreenSizes
@Composable
fun JaberApp(dialogs: ComposeDialogs) {
    AppNavHost(dialogs = dialogs, modifier = Modifier.fillMaxSize())
}