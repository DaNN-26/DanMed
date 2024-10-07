package com.example.danmed.firebase.auth.signUp.domain

interface SignUpRepository {
    suspend fun signUp(
        email: String,
        pass: String
    )
}