package com.example.medicalapp.domain.model

enum class ValidationError {
    EmailBlank,
    EmailInvalid,
    PasswordTooShort,
    PasswordMissingDigit,
    PasswordMissingUppercase
}

data class ValidationResult(
    val successful: Boolean,
    val error: ValidationError? = null
)
