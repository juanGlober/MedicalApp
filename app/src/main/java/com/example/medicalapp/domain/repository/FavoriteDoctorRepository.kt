package com.example.medicalapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteDoctorRepository {
    fun observeFavoriteDoctorIds(userId: String): Flow<Set<String>>
    suspend fun addFavorite(userId: String, doctorId: String)
    suspend fun removeFavorite(userId: String, doctorId: String)
}
