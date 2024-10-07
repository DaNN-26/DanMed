package com.example.danmed.ui.screens.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.db.domain.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    }

    private fun getAllMedicines() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMedicines().collect { items ->
                _uiState.value = StartState(medicineItems = items)
            }
        }
    }

    data class StartState(
        val medicineItems: List<Medicine> = listOf()
    )
}