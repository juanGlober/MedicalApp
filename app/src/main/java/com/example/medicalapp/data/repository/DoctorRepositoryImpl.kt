package com.example.medicalapp.data.repository

import com.example.medicalapp.data.local.dao.DoctorDao
import com.example.medicalapp.data.mapper.toDoctor
import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.repository.DoctorRepository
import com.example.medicalapp.domain.usecase.base.BaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val doctorDao: DoctorDao
) : DoctorRepository {

    private fun asException(throwable: Throwable): Exception =
        throwable as? Exception ?: Exception(throwable)

    override fun getAllDoctors(): Flow<BaseResult<List<Doctor>>> {
        return doctorDao.getAllDoctors()
            .map { entities -> entities.map { it.toDoctor() } }
            .map { doctors -> BaseResult.Success(doctors) as BaseResult<List<Doctor>> }
            .onStart { emit(BaseResult.Loading) }
            .catch { emit(BaseResult.Error(asException(it))) }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getDoctorById(id: String): BaseResult<Doctor> {
        return try {
            val doctor = withContext(Dispatchers.IO) {
                doctorDao.getDoctorById(id)?.toDoctor()
            }
            if (doctor != null) {
                BaseResult.Success(doctor)
            } else {
                BaseResult.Error(Exception("Doctor not found"))
            }
        } catch (e: Exception) {
            BaseResult.Error(e)
        }
    }

    override fun getDoctorsBySpecialty(specialty: String): Flow<BaseResult<List<Doctor>>> {
        return doctorDao.getDoctorsBySpecialty(specialty)
            .map { entities -> entities.map { it.toDoctor() } }
            .map { doctors -> BaseResult.Success(doctors) as BaseResult<List<Doctor>> }
            .onStart { emit(BaseResult.Loading) }
            .catch { emit(BaseResult.Error(asException(it))) }
            .flowOn(Dispatchers.IO)
    }
}
