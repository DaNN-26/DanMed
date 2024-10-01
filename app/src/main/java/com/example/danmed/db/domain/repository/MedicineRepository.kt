package com.example.danmed.db.domain.repository

import com.example.danmed.db.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun getAllMedicines() : Flow<List<Medicine>>

    suspend fun getMedicine(id: Int) : Flow<Medicine>

    suspend fun updateMedicine(medicine: Medicine)

    suspend fun deleteMedicine(medicine: Medicine)

    suspend fun insertNewMedicine(medicine: Medicine)
}