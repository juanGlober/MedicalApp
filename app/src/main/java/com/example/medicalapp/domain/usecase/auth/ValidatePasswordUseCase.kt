package com.example.medicalapp.domain.usecase.auth

import com.example.medicalapp.domain.model.ValidationResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password: String): ValidationResult {
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to be at least 6 characters"
            )
        }
        if (!password.any { it.isDigit() }) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to contain at least one digit"
            )
        }
        if (!password.any { it.isUpperCase() }) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to contain at least one uppercase letter"
            )
        }
        return ValidationResult(successful = true)
    }
}