package com.example.danmed.ui.screens.app.start

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danmed.db.domain.model.Medicine
import com.example.danmed.shared.getEmail
import com.example.danmed.shared.saveAuthState
import com.example.danmed.ui.navigation.NavigationDestination
import com.example.danmed.ui.screens.components.MedicineTopAppBar

object StartDestination : NavigationDestination {
    override val route = "start"
    override val title = "Список лекарств"
}
@Composable
fun StartScreen(
    viewModel: StartScreenViewModel,
    navigateToSignUp: () -> Unit,
    navigateToEntry: () -> Unit,
    navigateToDetails: (Int) -> Unit,
    uiState: State<StartScreenViewModel.StartState>
) {
    val context = LocalContext.current
    var isSideMenuOpen by remember { mutableStateOf(false) }
    var isAvailableItemsClicked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MedicineTopAppBar(
                title = StartDestination.title,
                canNavigateBack = false,
                isStartScreen = true,
                isAvailableItemsClicked= isAvailableItemsClicked,
                onAccountIconClick = {
                    isSideMenuOpen = !isSideMenuOpen
                },
                onAvailableItemsClick = {
                    if(!isAvailableItemsClicked){
                        viewModel.getAvailableMedicines()
                        isAvailableItemsClicked = true
                    } else {
                        viewModel.getAllMedicines()
                        isAvailableItemsClicked = false
                    }
                }
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
    ){ contentPadding ->
        BoxWithConstraints {
            val parentWidth = this.maxWidth / 1.7f
            val parentHeight = this.maxHeight

            StartBody(
                uiState = uiState,
                navigateToDetails = navigateToDetails,
                contentPadding = contentPadding
            )
            AnimatedVisibility(
                visible = isSideMenuOpen,
                enter = slideIn(spring(0.9f)) {
                    IntOffset(-500, 0)
                } + fadeIn(tween(300)),
                exit = slideOut(tween(400)) {
                    IntOffset(-500, 0)
                } + fadeOut(tween(700))
            ) {
                Box(
                    modifier = Modifier
                        .size(
                            width = parentWidth,
                            height = parentHeight
                        )
                        .shadow(12.dp)
                ) {
                    SidePanel(context, contentPadding) {
                        viewModel.signOut()
                        saveAuthState(context, false, "")
                        navigateToSignUp()
                    }
                }
            }
        }
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

@Composable
fun SidePanel(
    context: Context,
    contentPadding: PaddingValues,
    onButtonClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(contentPadding)
        ) {
            Text(
                text = "Email",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = getEmail(context),
                fontSize = 18.sp
            )
        }
        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(Color(72, 111, 126))
        ) {
            Text(
                text = "Выйти",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(211, 237, 247)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}