package com.example.danmed.firebase.auth.signIn.domain

import com.example.danmed.firebase.model.User

interface SignInRepository {
    suspend fun signIn(
        email: String,
        pass: String
    ): User
}