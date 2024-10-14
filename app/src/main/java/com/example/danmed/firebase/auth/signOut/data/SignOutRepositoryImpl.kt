package com.example.danmed.firebase.auth.signOut.data

import com.example.danmed.firebase.auth.signOut.domain.SignOutRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SignOutRepositoryImpl @Inject constructor(
    private val firebase: FirebaseAuth
) : SignOutRepository {
    override suspend fun signOut() {
        coroutineScope {
            firebase.signOut()
        }
    }
}