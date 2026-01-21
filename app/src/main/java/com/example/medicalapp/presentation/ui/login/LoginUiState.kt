package com.example.medicalapp.presentation.ui.login

import androidx.annotation.StringRes
import com.example.medicalapp.domain.model.User

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    val isPasswordVisible: Boolean = false,
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    @StringRes val error: Int? = null,
    val isLoginSuccessful: Boolean = false,
    val user: User? = null
)
