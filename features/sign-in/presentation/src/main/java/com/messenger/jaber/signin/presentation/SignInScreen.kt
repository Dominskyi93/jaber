package com.messenger.jaber.signin.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elveum.container.Container
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ProgressButton
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.entities.InputField

fun ScreenScope.signInScreen() {

    toolbar = ScreenToolbar.Default(context.getString(R.string.signinscreen))
    val viewModel: SignInVM = viewmodel(SignInVM::class)

    content {
        val container: Container<SignInVM.State> by viewModel.stateFlow.collectAsStateWithLifecycle()

        ContainerView(
            container = container,
            onTryAgainAction = {}
        ) { state ->
            SignInContent(
                state = state,
                onSignInAction = viewModel::signIn,
                onSignUpAction = viewModel::signUp,
                onClearErrorMessages = viewModel::clearErrorMessages
            )
        }
    }
}

@Composable
fun SignInContent(
    state: SignInVM.State,
    onSignInAction: (Credentials) -> Unit,
    onSignUpAction: () -> Unit,
    onClearErrorMessages: () -> Unit
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = Dimens.MediumPadding,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(Dimens.MediumPadding)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_network),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
        )

        SignInTextField(
            valueState = email,
            inputField = InputField.LOGIN,
            state = state,
            onClearErrorMessages = onClearErrorMessages
        )

        SignInTextField(
            valueState = password,
            inputField = InputField.PASSWORD,
            state = state,
            visualTransformation = PasswordVisualTransformation(),
            onClearErrorMessages = onClearErrorMessages,
            imeAction = ImeAction.Done
        )

        ProgressButton(
            isInProgress = state.isLoginInProgress,
            text = "Sign in"
        ) {
            onSignInAction(Credentials.Default(email.value, password.value))
        }

        TextButton(
            onClick = onSignUpAction,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Create account")
        }

        state.emptyFieldError?.let { error ->
            Spacer(Modifier.height(16.dp))
            Text(
                text = error.message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun SignInTextField(
    valueState: MutableState<String>,
    inputField: InputField,
    state: SignInVM.State,
    onClearErrorMessages: () -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            if (state.emptyFieldError?.inputField == inputField) {
                onClearErrorMessages()
            }
        },
        isError = state.emptyFieldError?.inputField == InputField.PASSWORD,
        supportingText = {
            if (state.emptyFieldError?.inputField == InputField.PASSWORD) {
                Text(state.emptyFieldError.message)
            }
        },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}