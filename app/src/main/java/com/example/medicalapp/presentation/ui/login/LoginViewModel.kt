package com.example.medicalapp.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.R
import com.example.medicalapp.data.local.session.SessionManager
import com.example.medicalapp.domain.model.ValidationError
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
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        val rememberedEmail = sessionManager.getRememberedEmail()
        if (!rememberedEmail.isNullOrBlank()) {
            _uiState.update {
                it.copy(email = rememberedEmail, rememberMe = true)
            }
        }
    }

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
        val emailResult: ValidationResult = validateEmailUseCase(_uiState.value.email)
        val passwordResult: ValidationResult = validatePasswordUseCase(_uiState.value.password)

        val hasError = listOf(emailResult, passwordResult).any { !it.successful }

        if (hasError) {
            _uiState.update {
                it.copy(
                    emailError = mapValidationError(emailResult.error),
                    passwordError = mapValidationError(passwordResult.error)
                )
            }
            return
        }
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
                        val email = _uiState.value.email.trim()
                        if (_uiState.value.rememberMe) {
                            sessionManager.setRememberedEmail(email)
                        } else {
                            sessionManager.clearRememberedEmail()
                        }
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
                                error = mapLoginError(result.exception)
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

    private fun mapValidationError(error: ValidationError?): Int? {
        return when (error) {
            ValidationError.EmailBlank -> R.string.error_email_blank
            ValidationError.EmailInvalid -> R.string.error_email_invalid
            ValidationError.PasswordTooShort -> R.string.error_password_too_short
            ValidationError.PasswordMissingDigit -> R.string.error_password_missing_digit
            ValidationError.PasswordMissingUppercase -> R.string.error_password_missing_uppercase
            null -> null
        }
    }

    private fun mapLoginError(error: Throwable): Int {
        return when (error.message) {
            "Invalid credentials" -> R.string.error_invalid_credentials
            "User already exists" -> R.string.error_user_already_exists
            else -> R.string.error_login_failed
        }
    }
}
