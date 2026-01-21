package com.example.medicalapp.data.repository

import com.example.medicalapp.data.local.dao.FavoriteDoctorDao
import com.example.medicalapp.data.local.entities.FavoriteDoctorEntity
import com.example.medicalapp.domain.repository.FavoriteDoctorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteDoctorRepositoryImpl @Inject constructor(
    private val favoriteDoctorDao: FavoriteDoctorDao
) : FavoriteDoctorRepository {

    override fun observeFavoriteDoctorIds(userId: String): Flow<Set<String>> {
        return favoriteDoctorDao.observeFavoriteDoctorIds(userId)
            .map { ids -> ids.toSet() }
    }

    override suspend fun addFavorite(userId: String, doctorId: String) {
        favoriteDoctorDao.insertFavorite(
            FavoriteDoctorEntity(userId = userId, doctorId = doctorId)
        )
    }

    override suspend fun removeFavorite(userId: String, doctorId: String) {
        favoriteDoctorDao.deleteFavorite(userId, doctorId)
    }
}
