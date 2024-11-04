package com.example.danmed.di

import com.example.danmed.firebase.auth.signIn.data.SignInRepositoryImpl
import com.example.danmed.firebase.auth.signIn.domain.SignInRepository
import com.example.danmed.firebase.auth.signOut.data.SignOutRepositoryImpl
import com.example.danmed.firebase.auth.signOut.domain.SignOutRepository
import com.example.danmed.firebase.auth.signUp.data.SignUpRepositoryImpl
import com.example.danmed.firebase.auth.signUp.domain.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideSignInRepository(): SignInRepository =
        SignInRepositoryImpl(provideFirebaseAuth())

    @Singleton
    @Provides
    fun provideSignUpRepository(): SignUpRepository =
        SignUpRepositoryImpl(provideFirebaseAuth())

    @Singleton
    @Provides
    fun provideSignOutRepository(): SignOutRepository =
        SignOutRepositoryImpl(provideFirebaseAuth())
}