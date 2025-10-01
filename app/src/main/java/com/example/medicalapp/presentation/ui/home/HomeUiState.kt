package com.example.medicalapp.presentation.ui.home

import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.model.User
import com.example.medicalapp.domain.model.Appointment

data class HomeUiState(
    val user: User? = null,
    val doctors: List<Doctor> = emptyList(),
    val upcomingAppointment: Appointment? = null,
    val searchQuery: String = "",
    val selectedTab: HomeTab = HomeTab.DOCTORS,
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class HomeTab {
    DOCTORS,
    FAVORITE,
    PROFILE
}