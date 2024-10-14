package com.example.danmed.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.danmed.shared.isUserLoggedIn
import com.example.danmed.ui.screens.detailsScreen.DetailsDestination
import com.example.danmed.ui.screens.detailsScreen.DetailsScreen
import com.example.danmed.ui.screens.detailsScreen.DetailsScreenViewModel
import com.example.danmed.ui.screens.editScreen.EditDestination
import com.example.danmed.ui.screens.editScreen.EditScreen
import com.example.danmed.ui.screens.editScreen.EditScreenViewModel
import com.example.danmed.ui.screens.entryScreen.EntryDestination
import com.example.danmed.ui.screens.entryScreen.EntryScreen
import com.example.danmed.ui.screens.entryScreen.EntryScreenViewModel
import com.example.danmed.ui.screens.signInScreen.SignInDestination
import com.example.danmed.ui.screens.signInScreen.SignInScreen
import com.example.danmed.ui.screens.signInScreen.SignInViewModel
import com.example.danmed.ui.screens.signUpScreen.SignUpDestination
import com.example.danmed.ui.screens.signUpScreen.SignUpScreen
import com.example.danmed.ui.screens.signUpScreen.SignUpViewModel
import com.example.danmed.ui.screens.startScreen.StartDestination
import com.example.danmed.ui.screens.startScreen.StartScreen
import com.example.danmed.ui.screens.startScreen.StartScreenViewModel

@Composable
fun MedicineNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = remember { navController.context}

    LaunchedEffect(Unit) {
        if(isUserLoggedIn(context)) {
            navController.navigate(StartDestination.route)
        }
        else {
            navController.navigate(SignUpDestination.route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = SignUpDestination.route,
        modifier = modifier
    ) {
        composable(route = SignUpDestination.route) {
            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(
                viewModel = signUpViewModel,
                uiState = signUpViewModel.uiState.collectAsState(),
                navigateToSignIn = { navController.navigate(SignInDestination.route) },
                navigateToStart = { navController.navigate(StartDestination.route) }
            )
        }
        composable(route = SignInDestination.route) {
            val signInViewModel = hiltViewModel<SignInViewModel>()
            SignInScreen(
                viewModel = signInViewModel,
                uiState = signInViewModel.uiState.collectAsState(),
                navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                navigateToStart = { navController.navigate(StartDestination.route) }
            )
        }
        composable(route = StartDestination.route) {
            val startViewModel = hiltViewModel<StartScreenViewModel>()
            StartScreen(
                viewModel = startViewModel,
                uiState = startViewModel.uiState.collectAsState(),
                navigateToSignUp = { navController.navigate(SignUpDestination.route) },
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