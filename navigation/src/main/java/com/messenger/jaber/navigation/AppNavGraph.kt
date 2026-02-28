package com.messenger.jaber.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import com.messenger.jaber.feature.chats.presentation.chatsScreen
import com.messenger.jaber.feature.main.presentation.Tab
import com.messenger.jaber.feature.main.presentation.mainScreen
import com.messenger.jaber.feature.presentation.initScreen
import com.messenger.jaber.feature.profile.presentation.profileScreen
import com.messenger.jaber.features.signup.presentation.congratsScreen
import com.messenger.jaber.features.signup.presentation.signUpScreen
import com.messenger.jaber.navigation.base.ExtendedNavGraphBuilder
import com.messenger.jaber.navigation.base.composable
import com.messenger.jaber.settings.presentation.settingsScreen
import com.messenger.jaber.signin.presentation.signInScreen
import com.messenger.jaber.feature.chats.presentation.R as ChatsR
import com.messenger.jaber.feature.profile.presentation.R as ProfileR
import com.messenger.jaber.feature.settings.presentation.R as SettingsR

fun ExtendedNavGraphBuilder.buildAppNavGraph() {
    composable<InitRoute> {
        initScreen()
    }
    composable<SignInRoute> {
        signInScreen()
    }
    composable<SignUpRoute> {
        signUpScreen()
    }
    composable<CongratsRoute> {
        congratsScreen()
    }
    composable<ChatsRoute> {
        chatsScreen()
    }

    composable<ProfileRoute> {
        profileScreen()
    }

    composable<SettingsRoute> {
        settingsScreen()
    }

    composable<MainRoute> {
        mainScreen(
            Tab(
                icon = Icons.Default.AccountBox,
                label = { getString(ProfileR.string.profile) },
                configuration = { profileScreen() }
            ),
            Tab(
                icon = Icons.AutoMirrored.Default.Message,
                label = { getString(ChatsR.string.chats_title) },
                configuration = { chatsScreen() }
            ),
            Tab(
                icon = Icons.Default.Settings,
                label = { getString(SettingsR.string.settings_title) },
                configuration = { settingsScreen() }
            )
        )
    }
}