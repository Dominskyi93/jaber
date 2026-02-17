package com.messenger.jaber.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import com.messenger.jaber.feature.chats.presentation.chatsScreen
import com.messenger.jaber.feature.main.presentation.Tab
import com.messenger.jaber.feature.main.presentation.mainScreen
import com.messenger.jaber.feature.presentation.initScreen
import com.messenger.jaber.features.signup.presentation.congratsScreen
import com.messenger.jaber.features.signup.presentation.signUpScreen
import com.messenger.jaber.navigation.base.ExtendedNavGraphBuilder
import com.messenger.jaber.navigation.base.composable
import com.messenger.jaber.signin.presentation.signInScreen
import com.messenger.jaber.feature.chats.presentation.R as ChatsR
import com.messenger.jaber.features.signup.presentation.R as CongratsR
import com.messenger.jaber.signin.presentation.R as SignInR

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
    //may remove later
    composable<ChatsRoute> {
        chatsScreen()
    }

    composable<MainRoute> {
        mainScreen(

            //change to Profile
            Tab(
                icon = Icons.Default.AccountBox,
                label = { getString(SignInR.string.sign_in) },
                configuration = { signInScreen() }
            ),
            Tab(
                icon = Icons.AutoMirrored.Default.Message,
                label = { getString(ChatsR.string.chats_title) },
                configuration = { chatsScreen() }
            ),
            //change to settings
            Tab(
                icon = Icons.Default.Settings,
                label = { getString(CongratsR.string.signup_congratulations_toolbar) },
                configuration = { congratsScreen() }
            )
        )
    }
}