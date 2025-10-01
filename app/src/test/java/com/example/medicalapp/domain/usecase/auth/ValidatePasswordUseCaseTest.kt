package com.example.medicalapp.domain.usecase.auth

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {

    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setUp() {
        validatePasswordUseCase = ValidatePasswordUseCase()
    }

    @Test
    fun `blank password returns error`() {
        val result = validatePasswordUseCase("")

        assertFalse(result.successful)
        assertEquals("Password can't be blank", result.errorMessage)
    }

    @Test
    fun `password less than 6 characters returns error`() {
        val result = validatePasswordUseCase("Test1")

        assertFalse(result.successful)
        assertEquals("Password needs to be at least 6 characters", result.errorMessage)
    }

    @Test
    fun `password without digit returns error`() {
        val result = validatePasswordUseCase("TestTest")

        assertFalse(result.successful)
        assertEquals("Password must contain at least one digit", result.errorMessage)
    }

    @Test
    fun `password without uppercase returns error`() {
        val result = validatePasswordUseCase("test123")

        assertFalse(result.successful)
        assertEquals("Password must contain at least one uppercase letter", result.errorMessage)
    }

    @Test
    fun `valid password returns success`() {
        val result = validatePasswordUseCase("Test123")

        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
}