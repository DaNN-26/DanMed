package com.example.danmed.ui.screens.signUpScreen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.danmed.ui.navigation.NavigationDestination
import com.example.danmed.ui.screens.components.MedicineTopAppBar
import com.example.danmed.ui.screens.components.Sign

object SignUpDestination : NavigationDestination {
    override val route = "sign_up"
    override val title = "Регистрация"
}

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    uiState: State<SignUpViewModel.SignUpState>,
    navigateToSignIn: () -> Unit,
    navigateToStart: () -> Unit
) {
    Scaffold(
        topBar = {
            MedicineTopAppBar(
                title = SignUpDestination.title,
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
                viewModel.onSignUp()
                if(uiState.value.isNotCorrectInput)
                    navigateToStart()
            },
            buttonText = "Зарегистрироваться",
            textButtonText = "Уже есть аккаунт?",
            onTextButtonClick = navigateToSignIn,
            emailSupportingText = "По примеру: example@gmail.com",
            passwordSupportingText = "Пароль должен содержать 8 и более символов\nПароль должен иметь в себе цифры",
            isError = uiState.value.isNotCorrectInput,
            contentPadding = contentPadding
        )
    }
}