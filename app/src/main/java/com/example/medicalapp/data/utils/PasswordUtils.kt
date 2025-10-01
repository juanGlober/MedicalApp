package com.example.medicalapp.data.utils

import android.util.Base64
import java.security.MessageDigest
import javax.inject.Inject

class PasswordUtils @Inject constructor() {

    fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }

    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        return hashPassword(password) == hashedPassword
    }
}