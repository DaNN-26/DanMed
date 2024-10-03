package com.example.danmed.ui.screens

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
    private fun getMedicine(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMedicine(id).collect {
                _uiState.value = DetailsState(it)
            }
        }
    }

    data class DetailsState(
        val medicine: Medicine = Medicine(
            name = "",
            description = "",
            amount = 0,
            price = 0
        )
    )
}