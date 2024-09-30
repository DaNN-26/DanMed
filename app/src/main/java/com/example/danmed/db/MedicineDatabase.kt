package com.example.danmed.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.db.domain.repository.MedicineDao
import com.example.danmed.db.domain.repository.MedicineRepository

@Database(
    entities = [Medicine::class],
    version = 1,
    exportSchema = false
)
abstract class MedicineDatabase : RoomDatabase() {

    abstract fun getMedicineDao(): MedicineDao
}