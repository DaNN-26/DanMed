package com.example.danmed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.di.AppModule

@Composable
fun StartScreen(
    viewModel: StartScreenViewModel = StartScreenViewModel(
        AppModule.provideMedicineRepository(
            AppModule.provideMedicineDao(
                AppModule.provideMedicineDatabase(
                    LocalContext.current
                )
            )
        )
    ),
    uiState: State<StartScreenViewModel.StartState> =
        viewModel.uiState.collectAsState(),
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .fillMaxSize()
            .padding(26.dp)
    ) {
        FloatingActionButton(
            onClick = { },
            shape = RoundedCornerShape(36.dp),
            modifier = Modifier.size(95.dp)
        ) {
            Text(
                "+",
                fontWeight = FontWeight.Normal,
                fontSize = 42.sp,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.Center,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(uiState.value.medicineItems) {
            MedicineItem(it, Modifier.padding(3.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineItem(
    medicine: Medicine,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { /*TODO*/ },
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        // Название
        Text(
            text = medicine.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${medicine.amount} шт.",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            modifier = modifier
        )
        Text(
            text = "${medicine.price} руб.",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            modifier = modifier
        )
    }
}