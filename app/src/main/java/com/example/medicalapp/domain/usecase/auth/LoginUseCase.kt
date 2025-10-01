package com.example.medicalapp.domain.usecase.auth

import com.example.medicalapp.domain.model.User
import com.example.medicalapp.domain.repository.AuthRepository
import com.example.medicalapp.domain.usecase.base.BaseResult
import com.example.medicalapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<LoginUseCase.Params, Flow<BaseResult<User>>> {

    data class Params(
        val email: String,
        val password: String
    )

    override suspend fun invoke(params: Params): Flow<BaseResult<User>> {
        return authRepository.login(params.email, params.password)
    }
}