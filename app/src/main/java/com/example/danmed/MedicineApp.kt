package com.example.danmed

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.danmed.ui.navigation.MedicineNavHost

@Composable
fun MedicineApp(
    navController: NavHostController = rememberNavController(),
) {
    MedicineNavHost(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = Modifier.shadow(12.dp)
    )
}