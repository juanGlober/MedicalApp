package com.example.medicalapp.domain.usecase.auth

import com.example.medicalapp.domain.repository.AuthRepository
import com.example.medicalapp.domain.usecase.base.BaseResult
import com.example.medicalapp.domain.usecase.base.NoParamsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : NoParamsUseCase<Flow<BaseResult<Unit>>> {

    override suspend fun invoke(): Flow<BaseResult<Unit>> {
        return authRepository.logout()
    }
}
