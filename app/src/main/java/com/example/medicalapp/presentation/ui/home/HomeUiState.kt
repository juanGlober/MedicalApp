package com.example.medicalapp.presentation.ui.home

import androidx.annotation.StringRes
import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.model.User
import com.example.medicalapp.domain.model.Appointment

data class HomeUiState(
    val user: User? = null,
    val doctors: List<Doctor> = emptyList(),
    val favoriteDoctorIds: Set<String> = emptySet(),
    val upcomingAppointment: Appointment? = null,
    val searchQuery: String = "",
    val selectedTab: HomeTab = HomeTab.DOCTORS,
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null
)

enum class HomeTab {
    DOCTORS,
    FAVORITE,
    PROFILE,
    CALENDAR
}
