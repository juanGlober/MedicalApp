package com.example.medicalapp.domain.repository

import com.example.medicalapp.domain.model.User
import com.example.medicalapp.domain.usecase.base.BaseResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<BaseResult<User>>
    suspend fun register(email: String, password: String, name: String): Flow<BaseResult<User>>
    suspend fun logout(): Flow<BaseResult<Unit>>
}