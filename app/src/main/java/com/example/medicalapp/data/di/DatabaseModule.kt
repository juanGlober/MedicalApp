package com.example.medicalapp.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.medicalapp.data.local.dao.UserDao
import com.example.medicalapp.data.local.dao.DoctorDao
import com.example.medicalapp.data.local.dao.FavoriteDoctorDao
import com.example.medicalapp.data.local.database.MedicalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS favorite_doctors (" +
                "userId TEXT NOT NULL, " +
                "doctorId TEXT NOT NULL, " +
                "PRIMARY KEY(userId, doctorId)" +
                ")"
        )
    }
}

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
            .addMigrations(MIGRATION_1_2)
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

    @Provides
    @Singleton
    fun provideFavoriteDoctorDao(database: MedicalDatabase): FavoriteDoctorDao {
        return database.favoriteDoctorDao()
    }
}
