package com.example.medicalapp.data.repository

import com.example.medicalapp.data.local.dao.UserDao
import com.example.medicalapp.data.local.entities.UserEntity
import com.example.medicalapp.data.mapper.toUser
import com.example.medicalapp.data.utils.PasswordUtils
import com.example.medicalapp.domain.model.User
import com.example.medicalapp.domain.repository.AuthRepository
import com.example.medicalapp.domain.usecase.base.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val passwordUtils: PasswordUtils
) : AuthRepository {

    override suspend fun login(email: String, password: String): Flow<BaseResult<User>> = flow {
        emit(BaseResult.Loading)
        try {
            val userEntity = userDao.getUserByEmail(email)
            if (userEntity != null && passwordUtils.verifyPassword(password, userEntity.password)) {
                emit(BaseResult.Success(userEntity.toUser()))
            } else {
                emit(BaseResult.Error(Exception("Invalid credentials")))
            }
        } catch (e: Exception) {
            emit(BaseResult.Error(e))
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String
    ): Flow<BaseResult<User>> = flow {
        emit(BaseResult.Loading)
        try {
            val existingUser = userDao.getUserByEmail(email)
            if (existingUser != null) {
                emit(BaseResult.Error(Exception("User already exists")))
            } else {
                val hashedPassword = passwordUtils.hashPassword(password)
                val newUser = UserEntity(
                    id = UUID.randomUUID().toString(),
                    email = email,
                    password = hashedPassword,
                    name = name,
                    displayName = name,
                    photoUrl = null,
                    isEmailVerified = false
                )
                userDao.insertUser(newUser)
                emit(BaseResult.Success(newUser.toUser()))
            }
        } catch (e: Exception) {
            emit(BaseResult.Error(e))
        }
    }

    override suspend fun logout(): Flow<BaseResult<Unit>> = flow {
        emit(BaseResult.Loading)
        try {
            emit(BaseResult.Success(Unit))
        } catch (e: Exception) {
            emit(BaseResult.Error(e))
        }
    }
}