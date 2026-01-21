package com.example.medicalapp.presentation.ui.profile

import androidx.annotation.StringRes

data class ProfileUiState(
    val isLoggingOut: Boolean = false,
    val isLogoutSuccessful: Boolean = false,
    @StringRes val error: Int? = null
)
