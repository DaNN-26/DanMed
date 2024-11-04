package com.example.danmed.ui.screens.auth.signUp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.danmed.shared.saveAuthState
import com.example.danmed.ui.navigation.NavigationDestination
import com.example.danmed.ui.screens.components.MedicineTopAppBar
import com.example.danmed.ui.screens.components.Sign
import kotlinx.coroutines.delay

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
    val context = LocalContext.current

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
            onButtonClick = viewModel::signUp,
            buttonText = "Зарегистрироваться",
            textButtonText = "Уже есть аккаунт?",
            onTextButtonClick = navigateToSignIn,
            emailSupportingText = "По примеру: example@gmail.com",
            passwordSupportingText = "Пароль должен содержать 6 и более символов",
            isError = uiState.value.isIncorrectInput,
            contentPadding = contentPadding
        )

        if(uiState.value.isSuccessfulSignUp)
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
                    Text(text = "Успешная регистрация")
                },
                text = {
                    Text(text = "Поздравляем, вы успешно прошли регистрацию!")
                }
            )
        if(uiState.value.isUserExists) {
            Snackbar(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(16.dp),
            ) {
                Text(text = "Пользователь с таким email уже существует")
            }
        }
        if(uiState.value.isUserExists)
            UserExistSnackbar(
                viewModel = viewModel,
                contentPadding = contentPadding
            )
    }
}

@Composable
fun UserExistSnackbar(
    viewModel: SignUpViewModel,
    contentPadding: PaddingValues
) {
    Snackbar(
        modifier = Modifier
            .padding(contentPadding)
            .padding(16.dp),
    ) {
        Text(text = "Пользователь с таким email уже существует")
    }
    LaunchedEffect(Unit) {
        delay(3000)
        viewModel.updateIsUserExists(false)
    }
}