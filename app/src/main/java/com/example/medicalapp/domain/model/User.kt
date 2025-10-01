package com.example.medicalapp.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val displayName: String?,
    val photoUrl: String?,
    val isEmailVerified: Boolean
)