package com.example.danmed.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.danmed.ui.screens.EntryDestination
import com.example.danmed.ui.screens.EntryScreen
import com.example.danmed.ui.screens.EntryScreenViewModel
import com.example.danmed.ui.screens.StartDestination
import com.example.danmed.ui.screens.StartScreen
import com.example.danmed.ui.screens.StartScreenViewModel

@Composable
fun MedicineNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route,
        modifier = modifier
    ) {
        composable(route = StartDestination.route) {
            val startViewModel = hiltViewModel<StartScreenViewModel>()
            StartScreen(
                viewModel = startViewModel,
                uiState = startViewModel.uiState.collectAsState(),
                navigateToEntry = { navController.navigate(EntryDestination.route) }
            )
        }
        composable(route = EntryDestination.route) {
            val entryViewModel = hiltViewModel<EntryScreenViewModel>()
            EntryScreen(
                viewModel = entryViewModel,
                uiState = entryViewModel.uiState.collectAsState(),
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}