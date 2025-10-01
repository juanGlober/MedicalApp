package com.example.medicalapp.data.repository

import com.example.medicalapp.data.local.dao.DoctorDao
import com.example.medicalapp.data.mapper.toDoctor
import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.repository.DoctorRepository
import com.example.medicalapp.domain.usecase.base.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val doctorDao: DoctorDao
) : DoctorRepository {

    override fun getAllDoctors(): Flow<BaseResult<List<Doctor>>> = flow {
        emit(BaseResult.Loading)
        try {
            doctorDao.getAllDoctors()
                .map { entities ->
                    entities.map { it.toDoctor() }
                }
                .collect { doctors ->
                    emit(BaseResult.Success(doctors))
                }
        } catch (e: Exception) {
            emit(BaseResult.Error(e))
        }
    }

    override suspend fun getDoctorById(id: String): BaseResult<Doctor> {
        return try {
            val doctor = doctorDao.getDoctorById(id)?.toDoctor()
            if (doctor != null) {
                BaseResult.Success(doctor)
            } else {
                BaseResult.Error(Exception("Doctor not found"))
            }
        } catch (e: Exception) {
            BaseResult.Error(e)
        }
    }

    override fun getDoctorsBySpecialty(specialty: String): Flow<BaseResult<List<Doctor>>> = flow {
        emit(BaseResult.Loading)
        try {
            doctorDao.getDoctorsBySpecialty(specialty)
                .map { entities ->
                    entities.map { it.toDoctor() }
                }
                .collect { doctors ->
                    emit(BaseResult.Success(doctors))
                }
        } catch (e: Exception) {
            emit(BaseResult.Error(e))
        }
    }
}