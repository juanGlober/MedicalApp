package com.example.medicalapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.medicalapp.data.local.dao.UserDao
import com.example.medicalapp.data.local.dao.DoctorDao
import com.example.medicalapp.data.local.database.MedicalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMedicalDatabase(
        @ApplicationContext context: Context
    ): MedicalDatabase {
        return Room.databaseBuilder(
            context,
            MedicalDatabase::class.java,
            "medical_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MedicalDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideDoctorDao(database: MedicalDatabase): DoctorDao {
        return database.doctorDao()
    }
}