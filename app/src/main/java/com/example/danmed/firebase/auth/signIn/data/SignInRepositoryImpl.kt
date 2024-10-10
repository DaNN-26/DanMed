package com.example.danmed.firebase.auth.signIn.data

import com.example.danmed.firebase.auth.signIn.domain.SignInRepository
import com.example.danmed.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SignInRepositoryImpl @Inject constructor(
    private val firebase: FirebaseAuth,
) : SignInRepository {
    override suspend fun signIn(email: String, pass: String): User =
        suspendCoroutine { continuation ->
            firebase.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if(user != null) {
                        continuation.resume(value = User(email = user.email.orEmpty()))
                    }
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
}