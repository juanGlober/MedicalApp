package com.example.medicalapp.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Doctors : Screen("doctors")
    object Rating : Screen("rating")
    object Profile : Screen("profile")
    object EditProfile : Screen("profile/edit")
    object DoctorInfo : Screen(
        route = "doctor_info/{doctorId}",
        arguments = listOf(
            navArgument("doctorId") { type = NavType.StringType }
        )
    ) {
        fun createRoute(doctorId: String) = "doctor_info/$doctorId"
    }
    object Detail : Screen(
        route = "detail/{itemId}",
        arguments = listOf(
            navArgument("itemId") { type = NavType.StringType }
        )
    ) {
        fun createRoute(itemId: String) = "detail/$itemId"
    }
}
