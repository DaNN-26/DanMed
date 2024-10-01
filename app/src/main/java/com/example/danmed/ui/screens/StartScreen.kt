package com.example.danmed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.ui.navigation.NavigationDestination

object StartDestination : NavigationDestination {
    override val route = "start"
    override val title = "Список лекарств"
}

@Composable
fun StartScreen(
    viewModel: StartScreenViewModel,
    navigateToEntry: () -> Unit,
    uiState: State<StartScreenViewModel.StartState>,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .fillMaxSize()
            .padding(26.dp)
    ) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            columns = GridCells.Adaptive(200.dp),
            modifier = modifier.fillMaxSize()
        ) {
            items(uiState.value.medicineItems) {
                MedicineItem(it, Modifier.padding(3.dp))
            }
        }
        FloatingActionButton(
            onClick = { navigateToEntry() },
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineItem(
    medicine: Medicine,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {  },
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier.padding(6.dp)
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