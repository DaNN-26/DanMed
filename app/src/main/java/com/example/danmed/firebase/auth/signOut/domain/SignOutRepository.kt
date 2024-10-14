package com.example.danmed.firebase.auth.signOut.domain

interface SignOutRepository {
    suspend fun signOut()
}