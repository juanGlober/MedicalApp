package com.example.medicalapp.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)