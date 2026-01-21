package com.example.medicalapp.data.local.entities

import androidx.room.Entity

@Entity(
    tableName = "favorite_doctors",
    primaryKeys = ["userId", "doctorId"]
)
data class FavoriteDoctorEntity(
    val userId: String,
    val doctorId: String
)
