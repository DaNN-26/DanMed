package com.example.danmed.ui.screens.signInScreen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
    navigateToSignUp: () -> Unit
) {
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
            onButtonClick = viewModel::signIn,
            buttonText = "Войти в аккаунт",
            textButtonText = "Создать аккаунт",
            onTextButtonClick = navigateToSignUp,
            contentPadding = contentPadding
        )
    }
}