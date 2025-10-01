package com.example.medicalapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.medicalapp.presentation.ui.login.LoginScreen
import com.example.medicalapp.presentation.ui.splash.SplashScreen
import com.example.medicalapp.presentation.ui.home.HomeScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToDetail = { doctorId ->
                    navController.navigate(Screen.Detail.createRoute(doctorId))
                },
                onNavigateToProfile = {
                    // TODO: Navigate to profile
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = Screen.Detail.arguments
        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("itemId") ?: ""
            // TODO: DetailScreen(doctorId = doctorId)
        }
    }
}