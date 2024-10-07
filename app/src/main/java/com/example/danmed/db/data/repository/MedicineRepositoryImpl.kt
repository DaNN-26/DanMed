package com.example.danmed.db.data.repository

import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.db.domain.repository.MedicineDao
import com.example.danmed.db.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineDao: MedicineDao
) : MedicineRepository {
    override suspend fun getAllMedicines(): Flow<List<Medicine>> =
        medicineDao.getAllMedicines()

    override suspend fun getAvailableMedicines(): Flow<List<Medicine>> =
        medicineDao.getAvailableMedicines()

    override suspend fun getMedicine(id: Int): Flow<Medicine> =
        medicineDao.getMedicine(id)

    override suspend fun updateMedicine(medicine: Medicine) =
        medicineDao.updateMedicine(medicine)

    override suspend fun deleteMedicine(medicine: Medicine) =
        medicineDao.deleteMedicine(medicine)

    override suspend fun insertNewMedicine(medicine: Medicine) =
        medicineDao.insertNewMedicine(medicine)
}