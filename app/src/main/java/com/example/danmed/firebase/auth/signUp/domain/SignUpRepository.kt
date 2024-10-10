package com.example.danmed.firebase.auth.signUp.domain

import com.example.danmed.firebase.model.User

interface SignUpRepository {
    suspend fun signUp(
        email: String,
        pass: String
    ): User
}