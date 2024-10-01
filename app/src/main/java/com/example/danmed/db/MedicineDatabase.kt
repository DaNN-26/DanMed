package com.example.danmed.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.db.domain.repository.MedicineDao

@Database(
    entities = [Medicine::class],
    version = 1,
    exportSchema = false
)
abstract class MedicineDatabase : RoomDatabase() {

    abstract fun getMedicineDao(): MedicineDao
}