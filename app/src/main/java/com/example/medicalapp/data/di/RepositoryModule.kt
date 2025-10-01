package com.example.medicalapp.data.di

import com.example.medicalapp.data.repository.AuthRepositoryImpl
import com.example.medicalapp.data.repository.DoctorRepositoryImpl
import com.example.medicalapp.domain.repository.AuthRepository
import com.example.medicalapp.domain.repository.DoctorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDoctorRepository(
        doctorRepositoryImpl: DoctorRepositoryImpl
    ): DoctorRepository
}