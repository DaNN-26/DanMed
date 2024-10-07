package com.example.danmed.firebase.auth.signUp.data

import com.example.danmed.firebase.auth.signUp.domain.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignUpRepositoryImpl(
    private val firebase: FirebaseAuth,
    private val cs: CoroutineScope
) : SignUpRepository {
    override suspend fun signUp(email: String, pass: String) {
        cs.launch {
            try {
                firebase.signInWithEmailAndPassword(email, pass)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}