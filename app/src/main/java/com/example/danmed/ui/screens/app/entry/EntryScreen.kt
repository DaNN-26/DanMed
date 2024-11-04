package com.example.danmed.ui.screens.app.entry

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

object EntryDestination : NavigationDestination {
    override val route = "entry"
    override val title = "Добавление"
}

@Composable
fun EntryScreen(
    viewModel: EntryScreenViewModel,
    uiState: State<EntryScreenViewModel.EntryState>,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            MedicineTopAppBar(
                title = EntryDestination.title,
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        }
    ) {
        EntryBody(
            viewModel = viewModel,
            uiState = uiState,
            navigateBack = navigateBack,
            contentPadding = it
        )
    }
}

@Composable
fun EntryBody(
    viewModel: EntryScreenViewModel,
    uiState: State<EntryScreenViewModel.EntryState>,
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
            text = "Добавление\nнового лекарства",
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
                viewModel.insertMedicine()
                navigateBack()
            },
            enabled = if (uiState.value.name == "") false
            else if (uiState.value.description == "") false
            else if (uiState.value.amount == "") false
            else if (uiState.value.price == "") false
            else true
        ) {
            Text(
                text = "Добавить",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}