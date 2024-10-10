package com.example.danmed.firebase.auth.signUp.data

import com.example.danmed.firebase.auth.signUp.domain.SignUpRepository
import com.example.danmed.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SignUpRepositoryImpl @Inject constructor(
    private val firebase: FirebaseAuth
) : SignUpRepository {
    override suspend fun signUp(email: String, pass: String): User =
        suspendCoroutine { continuation ->
            firebase.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if (user != null) {
                        continuation.resume(value = User(email = user.email.orEmpty()))
                    }
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
}