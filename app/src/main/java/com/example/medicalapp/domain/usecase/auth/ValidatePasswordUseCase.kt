package com.example.medicalapp.domain.usecase.auth

import com.example.medicalapp.domain.model.ValidationError
import com.example.medicalapp.domain.model.ValidationResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password: String): ValidationResult {
        if (password.length < MIN_PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                error = ValidationError.PasswordTooShort
            )
        }
        if (!password.any { it.isDigit() }) {
            return ValidationResult(
                successful = false,
                error = ValidationError.PasswordMissingDigit
            )
        }
        if (!password.any { it.isUpperCase() }) {
            return ValidationResult(
                successful = false,
                error = ValidationError.PasswordMissingUppercase
            )
        }
        return ValidationResult(successful = true)
    }

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }
}
