package com.example.medicalapp.presentation.ui.doctor

import com.example.medicalapp.domain.model.Doctor

object DoctorSampleData {
    val doctors = listOf(
        Doctor(
            id = "1",
            name = "Dr. Olivia Turner, M.D.",
            specialty = "Dermato-Endocrinology",
            photoUrl = null,
            rating = 4.8f,
            reviewCount = 60,
            isAvailable = true,
            nextAvailableTime = "Today, 11:00 AM",
            experience = "15 years",
            location = "Main Clinic"
        ),
        Doctor(
            id = "2",
            name = "Dr. Alexander Bennett, Ph.D.",
            specialty = "Pediatric Genetics",
            photoUrl = null,
            rating = 4.6f,
            reviewCount = 45,
            isAvailable = true,
            nextAvailableTime = "Today, 2:00 PM",
            experience = "12 years",
            location = "Skin First Center"
        ),
        Doctor(
            id = "3",
            name = "Dr. Sophia Martinez, Ph.D.",
            specialty = "Cosmetic Bioengineering",
            photoUrl = null,
            rating = 4.9f,
            reviewCount = 80,
            isAvailable = false,
            nextAvailableTime = "Tomorrow, 9:00 AM",
            experience = "10 years",
            location = "City Clinic"
        ),
        Doctor(
            id = "4",
            name = "Dr. Michael Davidson, M.D.",
            specialty = "Nano-Dermatology",
            photoUrl = null,
            rating = 4.7f,
            reviewCount = 55,
            isAvailable = true,
            nextAvailableTime = "Today, 4:00 PM",
            experience = "14 years",
            location = "Downtown Office"
        )
    )
}
