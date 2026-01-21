package com.example.medicalapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.medicalapp.presentation.ui.doctor.DoctorInfoScreen
import com.example.medicalapp.presentation.ui.doctor.DoctorsScreen
import com.example.medicalapp.presentation.ui.doctor.RatingScreen
import com.example.medicalapp.presentation.ui.home.HomeScreen
import com.example.medicalapp.presentation.ui.login.LoginScreen
import com.example.medicalapp.presentation.ui.profile.EditProfileScreen
import com.example.medicalapp.presentation.ui.profile.ProfileScreen
import com.example.medicalapp.presentation.ui.splash.SplashScreen

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
                    navController.navigate(Screen.DoctorInfo.createRoute(doctorId))
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateToDoctors = {
                    navController.navigate(Screen.Doctors.route)
                }
            )
        }

        composable(route = Screen.Doctors.route) {
            DoctorsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDoctorInfo = { doctorId ->
                    navController.navigate(Screen.DoctorInfo.createRoute(doctorId))
                },
                onNavigateToRating = { navController.navigate(Screen.Rating.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                }
            )
        }

        composable(
            route = Screen.DoctorInfo.route,
            arguments = Screen.DoctorInfo.arguments
        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("doctorId").orEmpty()
            DoctorInfoScreen(
                doctorId = doctorId,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToRating = { navController.navigate(Screen.Rating.route) }
            )
        }

        composable(route = Screen.Rating.route) {
            RatingScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                },
                onNavigateToDoctors = { navController.navigate(Screen.Doctors.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onEditProfile = { navController.navigate(Screen.EditProfile.route) },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                },
                onNavigateToDoctors = { navController.navigate(Screen.Doctors.route) },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.EditProfile.route) {
            EditProfileScreen(
                onNavigateBack = { navController.popBackStack() }
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
