package com.example.medicalapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val specialty: String,
    val photoUrl: String?,
    val rating: Float,
    val reviewCount: Int,
    val isAvailable: Boolean,
    val nextAvailableTime: String?,
    val experience: String?,
    val location: String?
)