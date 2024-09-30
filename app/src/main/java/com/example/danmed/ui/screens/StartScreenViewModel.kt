package com.example.danmed.ui.screens

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.db.domain.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val repository: MedicineRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(StartState())
    val uiState: StateFlow<StartState> = _uiState.asStateFlow()

    init {
        getAllMedicines()
        Log.d("Error", "${uiState.value.medicineItems.size}")
    }

    private fun getAllMedicines() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMedicines().collect { items ->
                _uiState.value = StartState(medicineItems = items)
            }
        }
    }

    fun insertNewMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNewMedicine(medicine)
        }
    }

    data class StartState(
        val medicineItems: List<Medicine> = listOf()
    )
}