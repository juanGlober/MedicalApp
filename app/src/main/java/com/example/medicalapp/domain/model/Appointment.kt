package com.example.medicalapp.domain.model

import java.util.Date

data class Appointment(
    val id: String,
    val doctorId: String,
    val doctorName: String,
    val specialty: String,
    val date: Date,
    val time: String,
    val type: AppointmentType,
    val status: AppointmentStatus
)

enum class AppointmentType {
    CONSULTATION,
    TREATMENT,
    FOLLOW_UP,
    EMERGENCY
}

enum class AppointmentStatus {
    SCHEDULED,
    CONFIRMED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}