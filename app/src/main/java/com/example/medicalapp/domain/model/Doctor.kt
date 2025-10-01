package com.example.medicalapp.domain.model

data class Doctor(
    val id: String,
    val name: String,
    val specialty: String,
    val photoUrl: String?,
    val rating: Float,
    val reviewCount: Int,
    val isAvailable: Boolean = true,
    val nextAvailableTime: String? = null,
    val experience: String? = null,
    val location: String? = null
)