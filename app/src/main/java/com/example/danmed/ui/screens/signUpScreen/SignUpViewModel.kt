package com.example.danmed.ui.screens.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.firebase.auth.signUp.domain.SignUpRepository
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
            repository.signUp(
                email = uiState.value.email,
                pass = uiState.value.pass
            )
        }
    }
    fun updateUiState(state: SignUpState) {
        _uiState.value = state
    }

    data class SignUpState(
        val email: String = "",
        val pass: String = ""
    )
}