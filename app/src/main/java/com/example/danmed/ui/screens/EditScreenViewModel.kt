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
class EditScreenViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditState())
    val uiState = _uiState.asStateFlow()
    init {
        getMedicine(EditDestination.argId)
    }
    private fun getMedicine(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
          repository.getMedicine(id).collect {
              _uiState.value = EditState(
                  name = it.name,
                  description = it.description,
                  amount = "${it.amount}",
                  price = "${it.price}"
              )
          }
        }
    }
    fun updateMedicine() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMedicine(uiState.value.toMedicine())
        }
    }
    fun updateUiState(state: EditState) {
        _uiState.value = state
    }
    private fun EditState.toMedicine(): Medicine =
        Medicine(
            id = id,
            name = name,
            description = description,
            amount = amount.toInt(),
            price = price.toInt()
        )
    data class EditState(
        val id: Int = EditDestination.argId,
        val name: String = "",
        val description: String = "",
        val amount: String = "",
        val price: String = ""
    )
}