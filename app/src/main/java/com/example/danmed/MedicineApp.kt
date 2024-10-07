package com.example.danmed

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.danmed.ui.navigation.MedicineNavHost

@Composable
fun MedicineApp(
    navController: NavHostController = rememberNavController(),
) {
    MedicineNavHost(navController = navController)
}