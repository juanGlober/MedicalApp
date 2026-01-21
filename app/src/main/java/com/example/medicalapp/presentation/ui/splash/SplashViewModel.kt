package com.example.medicalapp.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.local.dao.UserDao
import com.example.medicalapp.data.local.entities.UserEntity
import com.example.medicalapp.data.utils.PasswordUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userDao: UserDao,
    private val passwordUtils: PasswordUtils
) : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                createTestUserIfNeeded()
            }
            delay(SPLASH_DELAY_MS)
            _isReady.value = true
        }
    }

    private suspend fun createTestUserIfNeeded() {
        val existingUser = userDao.getUserByEmail(TEST_USER_EMAIL)

        if (existingUser == null) {
            val testUser = UserEntity(
                id = TEST_USER_ID,
                email = TEST_USER_EMAIL,
                password = passwordUtils.hashPassword(TEST_USER_PASSWORD),
                name = TEST_USER_NAME,
                displayName = TEST_USER_NAME,
                photoUrl = null,
                isEmailVerified = true
            )
            userDao.insertUser(testUser)

            val doctorUser = UserEntity(
                id = DOCTOR_USER_ID,
                email = DOCTOR_USER_EMAIL,
                password = passwordUtils.hashPassword(DOCTOR_USER_PASSWORD),
                name = DOCTOR_USER_NAME,
                displayName = DOCTOR_USER_NAME,
                photoUrl = null,
                isEmailVerified = true
            )
            userDao.insertUser(doctorUser)
        }
    }

    private companion object {
        const val SPLASH_DELAY_MS = 2000L
        const val TEST_USER_ID = "test-user-001"
        const val TEST_USER_EMAIL = "test@skinfirst.com"
        const val TEST_USER_PASSWORD = "Test123"
        const val TEST_USER_NAME = "Test User"
        const val DOCTOR_USER_ID = "doctor-001"
        const val DOCTOR_USER_EMAIL = "doctor@skinfirst.com"
        const val DOCTOR_USER_PASSWORD = "Doctor123"
        const val DOCTOR_USER_NAME = "Dr. Smith"
    }
}
