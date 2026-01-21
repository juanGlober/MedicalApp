package com.example.medicalapp.data.local.session

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class SessionManager @Inject constructor(
    private val preferences: SharedPreferences
) {
    fun setCurrentUserId(userId: String) {
        preferences.edit { putString(KEY_USER_ID, userId) }
    }

    fun getCurrentUserId(): String? {
        return preferences.getString(KEY_USER_ID, null)
    }

    fun clearCurrentUser() {
        preferences.edit { remove(KEY_USER_ID) }
    }

    fun setRememberedEmail(email: String) {
        preferences.edit { putString(KEY_REMEMBERED_EMAIL, email) }
    }

    fun getRememberedEmail(): String? {
        return preferences.getString(KEY_REMEMBERED_EMAIL, null)
    }

    fun clearRememberedEmail() {
        preferences.edit { remove(KEY_REMEMBERED_EMAIL) }
    }

    private companion object {
        const val KEY_USER_ID = "current_user_id"
        const val KEY_REMEMBERED_EMAIL = "remembered_email"
    }
}
