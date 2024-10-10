package com.example.danmed.ui.screens.signInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.firebase.auth.signIn.domain.SignInRepository
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
            repository.signIn(
                email = uiState.value.email,
                pass = uiState.value.pass
            )
        }
    }
    fun updateUiState(state: SignInState) {
        _uiState.value = state
    }

    data class SignInState(
        val email: String = "",
        val pass: String = ""
    )
}