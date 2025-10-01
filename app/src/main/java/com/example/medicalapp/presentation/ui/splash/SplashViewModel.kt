package com.example.medicalapp.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.data.local.dao.UserDao
import com.example.medicalapp.data.local.entities.UserEntity
import com.example.medicalapp.data.utils.PasswordUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
            createTestUserIfNeeded()
            delay(2000)
            _isReady.value = true
        }
    }

    private suspend fun createTestUserIfNeeded() {
        val testEmail = "test@skinfirst.com"
        val existingUser = userDao.getUserByEmail(testEmail)

        if (existingUser == null) {
            val testUser = UserEntity(
                id = "test-user-001",
                email = testEmail,
                password = passwordUtils.hashPassword("Test123"),
                name = "Test User",
                displayName = "Test User",
                photoUrl = null,
                isEmailVerified = true
            )
            userDao.insertUser(testUser)

            val doctorUser = UserEntity(
                id = "doctor-001",
                email = "doctor@skinfirst.com",
                password = passwordUtils.hashPassword("Doctor123"),
                name = "Dr. Smith",
                displayName = "Dr. Smith",
                photoUrl = null,
                isEmailVerified = true
            )
            userDao.insertUser(doctorUser)
        }
    }
}