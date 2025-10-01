package com.example.medicalapp.data.mapper

import com.example.medicalapp.data.local.entities.DoctorEntity
import com.example.medicalapp.domain.model.Doctor

fun DoctorEntity.toDoctor(): Doctor {
    return Doctor(
        id = id,
        name = name,
        specialty = specialty,
        photoUrl = photoUrl,
        rating = rating,
        reviewCount = reviewCount,
        isAvailable = isAvailable,
        nextAvailableTime = nextAvailableTime,
        experience = experience,
        location = location
    )
}

fun Doctor.toDoctorEntity(): DoctorEntity {
    return DoctorEntity(
        id = id,
        name = name,
        specialty = specialty,
        photoUrl = photoUrl,
        rating = rating,
        reviewCount = reviewCount,
        isAvailable = isAvailable,
        nextAvailableTime = nextAvailableTime,
        experience = experience,
        location = location
    )
}