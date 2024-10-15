package com.example.danmed.ui.screens.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.firebase.auth.signUp.domain.SignUpRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
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

    fun signUp() {
        viewModelScope.launch {
            try {
                repository.signUp(
                    email = uiState.value.email,
                    pass = uiState.value.pass
                )
                updateUiStateAfterSignUp()
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _uiState.value = SignUpState(isIncorrectInput = true)
            } catch (e: FirebaseAuthUserCollisionException) {
                _uiState.value = SignUpState(isUserExists = true)
            }
        }
    }
    private fun updateUiStateAfterSignUp() {
        _uiState.value = SignUpState(
            email = uiState.value.email,
            pass = uiState.value.pass,
            isSuccessfulSignUp = true
        )
    }
    fun updateIsUserExists(isUserExists: Boolean) {
        _uiState.value = SignUpState(
            email = uiState.value.email,
            pass = uiState.value.pass,
            isUserExists = isUserExists
        )
    }
    fun updateUiState(state: SignUpState) {
        _uiState.value = state
    }

    data class SignUpState(
        val email: String = "",
        val pass: String = "",
        val isIncorrectInput: Boolean = false,
        val isUserExists: Boolean = false,
        val isSuccessfulSignUp: Boolean = false
    )
}