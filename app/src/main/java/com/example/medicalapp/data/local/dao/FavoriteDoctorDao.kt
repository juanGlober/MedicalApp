package com.example.medicalapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalapp.data.local.entities.FavoriteDoctorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDoctorDao {
    @Query("SELECT doctorId FROM favorite_doctors WHERE userId = :userId")
    fun observeFavoriteDoctorIds(userId: String): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteDoctorEntity)

    @Query("DELETE FROM favorite_doctors WHERE userId = :userId AND doctorId = :doctorId")
    suspend fun deleteFavorite(userId: String, doctorId: String)
}
