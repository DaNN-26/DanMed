package com.example.danmed.ui.screens.editScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.ui.navigation.NavigationDestination
import com.example.danmed.ui.screens.components.MedicineInputForm
import com.example.danmed.ui.screens.components.MedicineTopAppBar

object EditDestination : NavigationDestination {
    override val route = "edit"
    override val title = "Редактирование"
    var argId = 0
}

@Composable
fun EditScreen(
    viewModel: EditScreenViewModel,
    uiState: State<EditScreenViewModel.EditState>,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            MedicineTopAppBar(
                title = EditDestination.title,
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        }
    ) {
        EditBody(
            viewModel = viewModel,
            uiState = uiState,
            navigateBack = navigateBack,
            contentPadding = it
        )
    }
}

@Composable
fun EditBody(
    viewModel: EditScreenViewModel,
    uiState: State<EditScreenViewModel.EditState>,
    navigateBack: () -> Unit,
    contentPadding: PaddingValues
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
            .padding(contentPadding)
    ) {
        Text(
            text = "Редактирование\nлекарства",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        MedicineInputForm(
            nameValue = uiState.value.name,
            onNameValueChange = { viewModel.updateUiState(uiState.value.copy(name = it)) },
            descValue = uiState.value.description,
            onDescValueChange = { viewModel.updateUiState(uiState.value.copy(description = it)) },
            amountValue = uiState.value.amount,
            onAmountValueChange = { viewModel.updateUiState(uiState.value.copy(amount = it)) },
            priceValue = uiState.value.price,
            onPriceValueChange = { viewModel.updateUiState(uiState.value.copy(price = it)) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                viewModel.updateMedicine()
                navigateBack()
            },
            enabled = if (uiState.value.name == "") false
            else if (uiState.value.description == "") false
            else if (uiState.value.amount == "") false
            else if (uiState.value.price == "") false
            else true
        ) {
            Text(
                text = "Редактировать",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}