package com.example.medicalapp.domain.usecase.doctor

import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.repository.DoctorRepository
import com.example.medicalapp.domain.usecase.base.BaseResult
import com.example.medicalapp.domain.usecase.base.NoParamsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDoctorsUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) : NoParamsUseCase<Flow<BaseResult<List<Doctor>>>> {

    override suspend fun invoke(): Flow<BaseResult<List<Doctor>>> {
        return doctorRepository.getAllDoctors()
    }
}