package com.example.medicalapp.domain.usecase.auth

import com.example.medicalapp.domain.model.ValidationError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidateEmailUseCaseTest {

    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    @Before
    fun setUp() {
        validateEmailUseCase = ValidateEmailUseCase()
    }

    @Test
    fun `blank email returns error`() {
        val result = validateEmailUseCase("")

        assertFalse(result.successful)
        assertEquals(ValidationError.EmailBlank, result.error)
    }

    @Test
    fun `invalid email format returns error`() {
        val result = validateEmailUseCase("notanemail")

        assertFalse(result.successful)
        assertEquals(ValidationError.EmailInvalid, result.error)
    }

    @Test
    fun `valid email returns success`() {
        val result = validateEmailUseCase("test@example.com")

        assertTrue(result.successful)
        assertNull(result.error)
    }
}
