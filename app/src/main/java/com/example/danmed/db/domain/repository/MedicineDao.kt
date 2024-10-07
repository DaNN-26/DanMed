package com.example.danmed.db.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.danmed.db.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewMedicine(medicine: Medicine)

    @Update
    suspend fun updateMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Query("SELECT * from medicines WHERE id = :id")
    fun getMedicine(id: Int): Flow<Medicine>

    @Query("SELECT * from medicines ORDER BY name ASC")
    fun getAllMedicines(): Flow<List<Medicine>>

    @Query("SELECT * from medicines WHERE amount > 0")
    fun getAvailableMedicines(): Flow<List<Medicine>>
}