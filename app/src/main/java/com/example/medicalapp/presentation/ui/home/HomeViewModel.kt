package com.example.medicalapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.R
import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.model.User
import com.example.medicalapp.domain.repository.FavoriteDoctorRepository
import com.example.medicalapp.domain.usecase.base.BaseResult
import com.example.medicalapp.domain.usecase.doctor.GetAllDoctorsUseCase
import com.example.medicalapp.presentation.ui.doctor.DoctorSampleData
import com.example.medicalapp.data.local.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase,
    private val favoriteDoctorRepository: FavoriteDoctorRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        initializeSampleDoctors()
        loadDoctors()
        loadUserData()
        observeFavorites()
    }

    private fun loadDoctors() {
        viewModelScope.launch {
            getAllDoctorsUseCase().collect { result ->
                when (result) {
                    is BaseResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is BaseResult.Success -> {
                        val doctors = result.data.ifEmpty { DoctorSampleData.doctors }
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                doctors = doctors,
                                error = null
                            )
                        }
                    }
                    is BaseResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = R.string.error_loading_doctors,
                                doctors = if (it.doctors.isEmpty()) {
                                    DoctorSampleData.doctors
                                } else {
                                    it.doctors
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadUserData() {
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
        _uiState.update { it.copy(doctors = DoctorSampleData.doctors) }
    }

    private fun observeFavorites() {
        val userId = sessionManager.getCurrentUserId()
        if (userId.isNullOrBlank()) {
            _uiState.update { it.copy(favoriteDoctorIds = emptySet()) }
            return
        }
        viewModelScope.launch {
            favoriteDoctorRepository.observeFavoriteDoctorIds(userId)
                .collect { favorites ->
                    _uiState.update { it.copy(favoriteDoctorIds = favorites) }
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onDoctorClick(doctor: Doctor) {
        // TODO() Doctor Detail
    }

    fun onFavoriteToggle(doctorId: String) {
        val userId = sessionManager.getCurrentUserId() ?: return
        val isFavorite = _uiState.value.favoriteDoctorIds.contains(doctorId)
        viewModelScope.launch {
            if (isFavorite) {
                favoriteDoctorRepository.removeFavorite(userId, doctorId)
            } else {
                favoriteDoctorRepository.addFavorite(userId, doctorId)
            }
        }
    }

    fun onTabSelected(tab: HomeTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }
}
