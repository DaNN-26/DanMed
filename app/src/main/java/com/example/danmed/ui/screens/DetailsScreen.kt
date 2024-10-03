package com.example.danmed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.MedicineTopAppBar
import com.example.danmed.ui.navigation.NavigationDestination

object DetailsDestination : NavigationDestination {
    override val route = "details"
    override val title = "Информация о лекарстве"
    var argId = 0
}

@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel,
    uiState: State<DetailsScreenViewModel.DetailsState>,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MedicineTopAppBar(
                title = DetailsDestination.title,
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = RoundedCornerShape(38.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .size(86.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    ) {
        DetailsBody(
            uiState = uiState,
            contentPadding = it
        )
    }
}

@Composable
fun DetailsBody(
    uiState: State<DetailsScreenViewModel.DetailsState>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(contentPadding)
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = uiState.value.medicine.name,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Описание:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Text(
            text = uiState.value.medicine.description,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Количество на складе:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Text(
            text = "${uiState.value.medicine.amount} шт.",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Цена:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Text(
            text = "${uiState.value.medicine.price} ₽",
            fontSize = 18.sp
        )
    }
}
