package com.example.medicalapp.domain.repository

import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.usecase.base.BaseResult
import kotlinx.coroutines.flow.Flow

interface DoctorRepository {
    fun getAllDoctors(): Flow<BaseResult<List<Doctor>>>
    suspend fun getDoctorById(id: String): BaseResult<Doctor>
    fun getDoctorsBySpecialty(specialty: String): Flow<BaseResult<List<Doctor>>>
}