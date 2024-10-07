package com.example.danmed.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.MedicineTopAppBar
import com.example.danmed.ui.navigation.NavigationDestination

object DetailsDestination : NavigationDestination {
    override val route = "details"
    override val title = "Информация о лекарстве"
    var argId: Int = 0
}

@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel,
    uiState: State<DetailsScreenViewModel.DetailsState>,
    navigateBack: () -> Unit,
    navigateToEdit: (Int) -> Unit
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
            AnimatedFloatingButtons(
                uiState = uiState,
                navigateToEdit = navigateToEdit,
                updateShowButtons = viewModel::updateShowButtons,
                updateOpenDialog = viewModel::updateOpenDialog
            )
        }
    ) {
        DetailsBody(
            uiState = uiState,
            contentPadding = it
        )
    }
    if(uiState.value.openDialog)
        DeleteConfirmDialogueBox(
            updateOpenDialog = viewModel::updateOpenDialog,
            deleteMedicine = viewModel::deleteMedicine,
            navigateBack = navigateBack
        )
}

@Composable
fun DetailsBody(
    uiState: State<DetailsScreenViewModel.DetailsState>,
    contentPadding: PaddingValues
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
            color = Color(27, 76, 96),
            fontSize = 24.sp
        )
        Text(
            text = uiState.value.medicine.description,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Justify,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Количество на складе:",
            fontWeight = FontWeight.SemiBold,
            color = Color(27, 76, 96),
            fontSize = 24.sp
        )
        Text(
            text = "${uiState.value.medicine.amount} шт.",
            fontWeight = FontWeight.Black,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Цена:",
            fontWeight = FontWeight.SemiBold,
            color = Color(27, 76, 96),
            fontSize = 24.sp
        )
        Text(
            text = "${uiState.value.medicine.price} ₽",
            fontWeight = FontWeight.Black,
            fontSize = 22.sp
        )
    }
}

@Composable
fun DeleteConfirmDialogueBox(
    updateOpenDialog: (Boolean) -> Unit,
    deleteMedicine: () -> Unit,
    navigateBack: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { updateOpenDialog(false) },
        confirmButton = {
            TextButton(onClick = {
                deleteMedicine()
                updateOpenDialog(false)
                navigateBack()
            }) {
                Text(
                    text = "ОК",
                    fontSize = 16.sp,
                    color = Color(35, 96, 121)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { updateOpenDialog(false) }) {
                Text(
                    text = "Отмена",
                    fontSize = 16.sp,
                    color = Color(169, 50, 38)
                )
            }
        },
        title = { Text(text = "Удаление") },
        text = { Text(text = "Вы действительно хотите удалить данный элемент?" +
                " Элемент удалится навсегда без возможности возврата.") },

        )
}

@Composable
fun AnimatedFloatingButtons(
    uiState: State<DetailsScreenViewModel.DetailsState>,
    navigateToEdit: (Int) -> Unit,
    updateShowButtons: (Boolean) -> Unit,
    updateOpenDialog: (Boolean) -> Unit
) {
    Column {
        AnimatedVisibility(
            visible = uiState.value.showButtons,
            enter = slideIn(spring(0.9f)) {
                IntOffset(2000, 0)
            } + fadeIn(tween(300)),
            exit = slideOut(tween(1000)) {
                IntOffset(2000, 0)
            } + fadeOut(tween(300))
        ) {
            Column {
                FloatingActionButton(
                    onClick = { navigateToEdit(uiState.value.medicine.id) },
                    shape = RoundedCornerShape(38.dp),
                    containerColor = Color(72, 111, 126),
                    contentColor = Color(211, 237, 247),
                    modifier = Modifier
                        .padding(6.dp)
                        .size(86.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
                FloatingActionButton(
                    onClick = { updateOpenDialog(true) },
                    shape = RoundedCornerShape(38.dp),
                    containerColor = Color(72, 111, 126),
                    contentColor = Color(211, 237, 247),
                    modifier = Modifier
                        .padding(6.dp)
                        .size(86.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = { updateShowButtons(!uiState.value.showButtons) },
            shape = RoundedCornerShape(38.dp),
            containerColor = Color(72, 111, 126),
            contentColor = Color(211, 237, 247),
            modifier = Modifier
                .padding(6.dp)
                .size(86.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
