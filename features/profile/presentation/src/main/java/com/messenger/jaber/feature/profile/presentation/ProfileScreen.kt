package com.messenger.jaber.feature.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar

fun ScreenScope.profileScreen() {

    toolbar = ScreenToolbar.Default(context.getString(R.string.profile))

    content {
        ProfileScreenContent()
    }
}

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {

    }
}