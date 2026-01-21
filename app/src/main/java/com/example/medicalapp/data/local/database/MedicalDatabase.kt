package com.example.medicalapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medicalapp.data.local.dao.UserDao
import com.example.medicalapp.data.local.dao.DoctorDao
import com.example.medicalapp.data.local.dao.FavoriteDoctorDao
import com.example.medicalapp.data.local.entities.UserEntity
import com.example.medicalapp.data.local.entities.DoctorEntity
import com.example.medicalapp.data.local.entities.FavoriteDoctorEntity

@Database(
    entities = [
        UserEntity::class,
        DoctorEntity::class,
        FavoriteDoctorEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MedicalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun doctorDao(): DoctorDao
    abstract fun favoriteDoctorDao(): FavoriteDoctorDao
}
