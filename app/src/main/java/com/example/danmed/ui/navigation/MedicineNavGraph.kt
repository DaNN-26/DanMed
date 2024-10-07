package com.example.danmed.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.danmed.ui.screens.editScreen.EditDestination
import com.example.danmed.ui.screens.editScreen.EditScreen
import com.example.danmed.ui.screens.editScreen.EditScreenViewModel
import com.example.danmed.ui.screens.entryScreen.EntryDestination
import com.example.danmed.ui.screens.entryScreen.EntryScreen
import com.example.danmed.ui.screens.entryScreen.EntryScreenViewModel
import com.example.danmed.ui.screens.startScreen.StartDestination
import com.example.danmed.ui.screens.startScreen.StartScreen
import com.example.danmed.ui.screens.startScreen.StartScreenViewModel
import com.example.danmed.ui.screens.detailsScreen.DetailsDestination
import com.example.danmed.ui.screens.detailsScreen.DetailsScreen
import com.example.danmed.ui.screens.detailsScreen.DetailsScreenViewModel

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
                uiState = startViewModel.uiState.collectAsState(),
                navigateToEntry = { navController.navigate(EntryDestination.route) },
                navigateToDetails = { id ->
                    navController.navigate(DetailsDestination.route)
                    DetailsDestination.argId = id
                }
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
        composable(route = DetailsDestination.route) {
            val detailsViewModel = hiltViewModel<DetailsScreenViewModel>()
            DetailsScreen(
                viewModel = detailsViewModel,
                uiState = detailsViewModel.uiState.collectAsState(),
                navigateBack = { navController.popBackStack() },
                navigateToEdit = { id ->
                    navController.navigate(EditDestination.route)
                    EditDestination.argId = id
                }
            )
        }
        composable(route = EditDestination.route) {
            val editViewModel = hiltViewModel<EditScreenViewModel>()
            EditScreen(
                viewModel = editViewModel,
                uiState = editViewModel.uiState.collectAsState(),
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}