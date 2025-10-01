package com.example.medicalapp.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.domain.model.ValidationResult
import com.example.medicalapp.domain.usecase.auth.LoginUseCase
import com.example.medicalapp.domain.usecase.auth.ValidateEmailUseCase
import com.example.medicalapp.domain.usecase.auth.ValidatePasswordUseCase
import com.example.medicalapp.domain.usecase.base.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onPasswordVisibilityToggle() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onRememberMeChange(remember: Boolean) {
        _uiState.update { it.copy(rememberMe = remember) }
    }

    fun onLogin() {
        // Validar campos
        val emailResult: ValidationResult = validateEmailUseCase(_uiState.value.email)
        val passwordResult: ValidationResult = validatePasswordUseCase(_uiState.value.password)

        // Verificar si hay errores
        val hasError = listOf(emailResult, passwordResult).any { !it.successful }

        if (hasError) {
            _uiState.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
            }
            return
        }

        // Proceder con el login si no hay errores
        performLogin()
    }

    private fun performLogin() {
        viewModelScope.launch {
            loginUseCase(
                LoginUseCase.Params(
                    email = _uiState.value.email.trim(),
                    password = _uiState.value.password
                )
            ).collect { result ->
                when (result) {
                    is BaseResult.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                    is BaseResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoginSuccessful = true,
                                user = result.data,
                                error = null
                            )
                        }
                    }
                    is BaseResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.exception.message ?: "Login failed. Please try again."
                            )
                        }
                    }
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun createTestUser() {
        viewModelScope.launch {
            // Este método se puede usar para crear un usuario de prueba
            // Email: test@skinfirst.com
            // Password: Test123
        }
    }
}