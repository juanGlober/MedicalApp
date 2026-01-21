package com.example.medicalapp.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.medicalapp.data.local.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("medical_session", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSessionManager(
        preferences: SharedPreferences
    ): SessionManager {
        return SessionManager(preferences)
    }
}
