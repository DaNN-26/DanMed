package com.example.danmed.ui.screens.startScreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.ui.navigation.NavigationDestination
import com.example.danmed.ui.screens.components.MedicineTopAppBar

object StartDestination : NavigationDestination {
    override val route = "start"
    override val title = "Список лекарств"
}
@Composable
fun StartScreen(
    navigateToEntry: () -> Unit,
    navigateToDetails: (Int) -> Unit,
    uiState: State<StartScreenViewModel.StartState>
) {
    Scaffold(
        topBar = {
            MedicineTopAppBar(
                title = StartDestination.title,
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEntry,
                shape = RoundedCornerShape(38.dp),
                containerColor = Color(72, 111, 126),
                contentColor = Color(211, 237, 247),
                modifier = Modifier
                    .padding(12.dp)
                    .size(86.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    ){
        StartBody(
            uiState = uiState,
            navigateToDetails = navigateToDetails,
            contentPadding = it
        )
    }
}
@Composable
fun StartBody(
    uiState: State<StartScreenViewModel.StartState>,
    navigateToDetails: (Int) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            columns = GridCells.Adaptive(200.dp),
            contentPadding = contentPadding,
            modifier = modifier.fillMaxSize()
        ) {
            items(uiState.value.medicineItems) { medicine ->
                MedicineItem(
                    medicine = medicine,
                    navigateToDetails = navigateToDetails,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineItem(
    medicine: Medicine,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { navigateToDetails(medicine.id) },
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(Color(211, 242, 254 )),
        modifier = Modifier.padding(6.dp)
    ) {
        Text(
            text = medicine.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp,
            modifier = modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${medicine.amount} шт.",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            modifier = modifier.padding(horizontal = 4.dp)
        )
        Text(
            text = "${medicine.price} ₽",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            modifier = modifier.padding(horizontal = 4.dp)
        )
    }
}