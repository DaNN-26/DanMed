package com.example.danmed.ui.screens.auth.signIn

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import com.example.danmed.shared.saveAuthState
import com.example.danmed.ui.navigation.NavigationDestination
import com.example.danmed.ui.screens.components.MedicineTopAppBar
import com.example.danmed.ui.screens.components.Sign

object SignInDestination : NavigationDestination {
    override val route = "sign_in"
    override val title = "Авторизация"
}

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    uiState: State<SignInViewModel.SignInState>,
    navigateToSignUp: () -> Unit,
    navigateToStart: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            MedicineTopAppBar(
                title = SignInDestination.title,
                canNavigateBack = false
            )
        }
    ) { contentPadding ->
        Sign(
            email = uiState.value.email,
            pass = uiState.value.pass,
            onEmailChange = { viewModel.updateUiState(uiState.value.copy(email = it)) },
            onPassChange = { viewModel.updateUiState(uiState.value.copy(pass = it)) },
            onButtonClick = {
                viewModel.signIn()
                if(uiState.value.isIncorrectUser)
                    navigateToStart()
            },
            buttonText = "Войти в аккаунт",
            textButtonText = "Создать аккаунт",
            onTextButtonClick = navigateToSignUp,
            emailSupportingText = "",
            passwordSupportingText = "",
            isError = uiState.value.isIncorrectUser,
            contentPadding = contentPadding
        )

        if(uiState.value.isSuccessfulSignIn)
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    TextButton(onClick = {
                        saveAuthState(context, true, uiState.value.email)
                        navigateToStart()
                    }) {
                        Text(text = "Ок")
                    }
                },
                title = {
                    Text(text = "Успешный вход")
                },
                text = {
                    Text(text = "Поздравляем, вы успешно вошли в аккаунт!")
                }
            )
    }
}