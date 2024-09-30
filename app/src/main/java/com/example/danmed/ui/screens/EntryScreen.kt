package com.example.danmed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.di.AppModule

@Composable
fun EntryScreen(
    viewModel: EntryScreenViewModel = EntryScreenViewModel(
        AppModule.provideMedicineRepository(
            AppModule.provideMedicineDao(
                AppModule.provideMedicineDatabase(
                    LocalContext.current
                )
            )
        )
    ),
    uiState: State<EntryScreenViewModel.EntryState> =
        viewModel.uiState.collectAsState(),
    modifier: Modifier = Modifier,
) {
    EntryBody(
        viewModel = viewModel,
        uiState = uiState,
        onValueChange = viewModel::updateUiState
    )
}

@Composable
fun EntryBody(
    viewModel: EntryScreenViewModel,
    uiState: State<EntryScreenViewModel.EntryState>,
    onValueChange: (EntryScreenViewModel.EntryState) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Добавление\nнового лекарства",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        EntryForm(uiState, onValueChange)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { viewModel.insertMedicine() }) {
            Text(
                text = "Добавить",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun EntryForm(
    uiState: State<EntryScreenViewModel.EntryState>,
    onValueChange: (EntryScreenViewModel.EntryState) -> Unit
) {
    Column {
        OutlinedTextField(
            value = uiState.value.name,
            onValueChange = { onValueChange(uiState.value.copy(name = it)) },
            label = { Text(text = "Название") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            enabled = true,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = uiState.value.description,
            onValueChange = { onValueChange(uiState.value.copy(description = it)) },
            label = { Text(text = "Описание") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            enabled = true,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = uiState.value.amount,
            onValueChange = { onValueChange(uiState.value.copy(amount = it)) },
            label = { Text(text = "Количество") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            suffix = { Text(text = "шт.") },
            enabled = true,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = uiState.value.price,
            onValueChange = { onValueChange(uiState.value.copy(price = it)) },
            label = { Text(text = "Цена") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            suffix = { Text(text = "₽") },
            enabled = true,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}