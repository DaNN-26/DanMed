package com.example.danmed.di

import android.content.Context
import androidx.room.Room
import com.example.danmed.db.MedicineDatabase
import com.example.danmed.db.data.repository.MedicineRepositoryImpl
import com.example.danmed.db.domain.repository.MedicineDao
import com.example.danmed.db.domain.repository.MedicineRepository
import com.example.danmed.firebase.auth.signIn.data.SignInRepositoryImpl
import com.example.danmed.firebase.auth.signIn.domain.SignInRepository
import com.example.danmed.firebase.auth.signUp.data.SignUpRepositoryImpl
import com.example.danmed.firebase.auth.signUp.domain.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMedicineDatabase(
        @ApplicationContext context: Context
    ): MedicineDatabase = Room.databaseBuilder(
        context,
        MedicineDatabase::class.java,
        "medicine_database"
    ).build()

    @Singleton
    @Provides
    fun provideFirebaseDatabase(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideSignInRepository(): SignInRepository =
        SignInRepositoryImpl(provideFirebaseDatabase())

    @Singleton
    @Provides
    fun provideSignUpRepository(): SignUpRepository =
        SignUpRepositoryImpl(provideFirebaseDatabase())

    @Provides
    fun provideMedicineDao(db: MedicineDatabase): MedicineDao =
        db.getMedicineDao()

    @Provides
    fun provideMedicineRepository(dao: MedicineDao): MedicineRepository =
        MedicineRepositoryImpl(dao)
}