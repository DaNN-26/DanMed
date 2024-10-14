package com.example.danmed.ui.screens.detailsScreen

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
class DetailsScreenViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailsState())
    val uiState = _uiState.asStateFlow()
    init {
        getMedicine(DetailsDestination.argId)
    }
    @Suppress("SENSELESS_COMPARISON")
    private fun getMedicine(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMedicine(id).collect {
                _uiState.value =
                    if(it == null) DetailsState()
                    else DetailsState(it)
            }
        }
    }
    fun deleteMedicine() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedicine(uiState.value.medicine)
        }
    }
    fun updateOpenDialog(openDialog: Boolean) {
        _uiState.value = uiState.value.copy(openDialog = openDialog)
    }
    fun updateShowButtons(showButtons: Boolean) {
        _uiState.value = uiState.value.copy(showButtons = showButtons)
    }
    data class DetailsState(
        val medicine: Medicine = Medicine(
            name = "",
            description = "",
            amount = 0,
            price = 0
        ),
        val openDialog: Boolean = false,
        val showButtons: Boolean = false
    )
}