package com.example.danmed.ui.screens.auth.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.firebase.auth.signIn.domain.SignInRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: SignInRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SignInState())
    val uiState = _uiState.asStateFlow()

    fun signIn() {
        viewModelScope.launch {
            try {
                repository.signIn(
                    email = uiState.value.email,
                    pass = uiState.value.pass
                )
                updateUiStateAfterSignIn()
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _uiState.value = SignInState(isIncorrectUser = true)
            }
        }
    }
    private fun updateUiStateAfterSignIn() {
        _uiState.value = SignInState(
            email = uiState.value.email,
            pass = uiState.value.pass,
            isSuccessfulSignIn = true
        )
    }
    fun updateUiState(state: SignInState) {
        _uiState.value = state
    }
    data class SignInState(
        val email: String = "",
        val pass: String = "",
        val isIncorrectUser: Boolean = false,
        val isSuccessfulSignIn: Boolean = false
    )
}