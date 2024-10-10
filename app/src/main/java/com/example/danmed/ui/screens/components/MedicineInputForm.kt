package com.example.danmed.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun MedicineInputForm(
    nameValue: String,
    onNameValueChange: (String) -> Unit,
    descValue: String,
    onDescValueChange: (String) -> Unit,
    amountValue: String,
    onAmountValueChange: (String) -> Unit,
    priceValue: String,
    onPriceValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = nameValue,
            onValueChange = { onNameValueChange(it) },
            label = { Text(text = "Название") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            enabled = true,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = descValue,
            onValueChange = { onDescValueChange(it) },
            label = { Text(text = "Описание") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            maxLines = 4,
            enabled = true,
            singleLine = false
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = amountValue,
            onValueChange = { onAmountValueChange(it) },
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
            value = priceValue,
            onValueChange = { onPriceValueChange(it) },
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