package com.example.danmed.firebase.auth.signIn.domain

interface SignInRepository {
    suspend fun signIn(
        email: String,
        pass: String
    )
}