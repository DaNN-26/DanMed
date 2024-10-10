package com.example.danmed.ui.screens.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.firebase.auth.signUp.domain.SignUpRepository
import com.example.danmed.util.EmailValidationResult
import com.example.danmed.util.EmailValidator
import com.example.danmed.util.PasswordValidationResult
import com.example.danmed.util.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState = _uiState.asStateFlow()

    private val emailValidator = EmailValidator()
    private val passValidator = PasswordValidator()
    private fun signUp() {
        viewModelScope.launch {
            repository.signUp(
                email = uiState.value.email,
                pass = uiState.value.pass
            )
        }
    }
    fun updateUiState(state: SignUpState) {
        _uiState.value = state
    }

    fun onSignUp() {
        val isEmailCorrect = when(emailValidator(uiState.value.email)) {
            EmailValidationResult.INCORRECT_FORMAT -> false
            else -> true
        }
        val isPassCorrect = when(passValidator(uiState.value.pass)) {
            PasswordValidationResult.NOT_LONG_ENOUGH -> false
            PasswordValidationResult.NOT_ENOUGH_DIGITS -> false
            else -> true
        }
        if(isEmailCorrect && isPassCorrect)
            signUp()
        else
            _uiState.value = SignUpState(isNotCorrectInput = true)
    }

    data class SignUpState(
        val email: String = "",
        val pass: String = "",
        val isNotCorrectInput: Boolean = false
    )
}