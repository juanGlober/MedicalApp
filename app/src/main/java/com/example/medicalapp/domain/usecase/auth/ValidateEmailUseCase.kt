package com.example.medicalapp.domain.usecase.auth

import android.util.Patterns
import com.example.medicalapp.domain.model.ValidationError
import com.example.medicalapp.domain.model.ValidationResult
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                error = ValidationError.EmailBlank
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                error = ValidationError.EmailInvalid
            )
        }
        return ValidationResult(successful = true)
    }
}
