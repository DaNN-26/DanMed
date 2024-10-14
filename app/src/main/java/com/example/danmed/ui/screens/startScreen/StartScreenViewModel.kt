package com.example.danmed.ui.screens.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.db.domain.repository.MedicineRepository
import com.example.danmed.firebase.auth.signOut.domain.SignOutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val roomRepository: MedicineRepository,
    private val signOutRepository: SignOutRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(StartState())
    val uiState: StateFlow<StartState> = _uiState.asStateFlow()

    init {
        getAllMedicines()
        getAvailableMedicines()
    }

    fun getAllMedicines() {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.getAllMedicines().collect { items ->
                _uiState.value = StartState(medicineItems = items)
            }
        }
    }

    fun getAvailableMedicines() {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.getAvailableMedicines().collect { items ->
                _uiState.value = StartState(medicineItems = items)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            signOutRepository.signOut()
        }
    }
    data class StartState(
        val medicineItems: List<Medicine> = listOf(),
        val availableMedicines: List<Medicine> = listOf()
    )
}