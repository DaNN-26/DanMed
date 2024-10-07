package com.example.danmed.firebase.auth.signIn.data

import com.example.danmed.firebase.auth.signIn.domain.SignInRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class SignInRepositoryImpl(
    private val firebase: FirebaseAuth,
    private val cs: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : SignInRepository {
    override suspend fun signIn(email: String, pass: String) {
        cs.launch {
            try {
                firebase.signInWithEmailAndPassword(email, pass)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}