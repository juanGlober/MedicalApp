package com.example.medicalapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medicalapp.data.local.dao.UserDao
import com.example.medicalapp.data.local.dao.DoctorDao
import com.example.medicalapp.data.local.entities.UserEntity
import com.example.medicalapp.data.local.entities.DoctorEntity

@Database(
    entities = [
        UserEntity::class,
        DoctorEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MedicalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun doctorDao(): DoctorDao
}