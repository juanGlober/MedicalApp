package com.example.medicalapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.model.User
import com.example.medicalapp.domain.usecase.base.BaseResult
import com.example.medicalapp.domain.usecase.doctor.GetAllDoctorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadDoctors()
        loadUserData()
        initializeSampleDoctors()
    }

    private fun loadDoctors() {
        viewModelScope.launch {
            getAllDoctorsUseCase().collect { result ->
                when (result) {
                    is BaseResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is BaseResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                doctors = result.data,
                                error = null
                            )
                        }
                    }
                    is BaseResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.exception.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadUserData() {
        // Simular datos del usuario
        _uiState.update {
            it.copy(
                user = User(
                    id = "1",
                    name = "John Doe",
                    email = "john.doe@example.com",
                    displayName = "John Doe",
                    photoUrl = null,
                    isEmailVerified = true
                )
            )
        }
    }

    private fun initializeSampleDoctors() {
        // Esta función se llamará una vez para crear doctores de muestra
        val sampleDoctors = listOf(
            Doctor(
                id = "1",
                name = "Dr. Olivia Turner, M.D.",
                specialty = "Dermato-Endocrinology",
                photoUrl = null,
                rating = 4.8f,
                reviewCount = 60,
                isAvailable = true,
                nextAvailableTime = "Today, 11:00 AM"
            ),
            Doctor(
                id = "2",
                name = "Dr. Alexander Bennett, Ph.D.",
                specialty = "Pediatric Genetics",
                photoUrl = null,
                rating = 4.6f,
                reviewCount = 45,
                isAvailable = true,
                nextAvailableTime = "Today, 2:00 PM"
            ),
            Doctor(
                id = "3",
                name = "Dr. Sophia Martinez, Ph.D.",
                specialty = "Cosmetic Bioengineering",
                photoUrl = null,
                rating = 4.9f,
                reviewCount = 80,
                isAvailable = false,
                nextAvailableTime = "Tomorrow, 9:00 AM"
            ),
            Doctor(
                id = "4",
                name = "Dr. Michael Davidson, M.D.",
                specialty = "Nano-Dermatology",
                photoUrl = null,
                rating = 4.7f,
                reviewCount = 55,
                isAvailable = true,
                nextAvailableTime = "Today, 4:00 PM"
            )
        )

        // Aquí normalmente insertarías en la base de datos
        _uiState.update { it.copy(doctors = sampleDoctors) }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        // Implementar búsqueda
    }

    fun onDoctorClick(doctor: Doctor) {
        // Navegar a detalle del doctor
    }

    fun onTabSelected(tab: HomeTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }
}