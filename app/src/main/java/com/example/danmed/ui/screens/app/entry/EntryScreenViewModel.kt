package com.example.danmed.ui.screens.app.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.db.domain.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EntryState())
    val uiState = _uiState.asStateFlow()

    fun updateUiState(state: EntryState) {
        _uiState.value = state
    }

    fun insertMedicine() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNewMedicine(uiState.value.toMedicine())
        }
    }

    private fun EntryState.toMedicine(): Medicine =
        Medicine(
            name = name,
            description = description,
            amount = amount.toInt(),
            price = price.toInt()
        )

    data class EntryState(
        val name: String = "",
        val description: String = "",
        val amount: String = "",
        val price: String = ""
    )
}