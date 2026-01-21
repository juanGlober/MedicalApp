package com.example.medicalapp.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapp.R
import com.example.medicalapp.domain.usecase.auth.LogoutUseCase
import com.example.medicalapp.domain.usecase.base.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onLogout() {
        if (_uiState.value.isLoggingOut) {
            return
        }
        viewModelScope.launch {
            logoutUseCase().collect { result ->
                when (result) {
                    is BaseResult.Loading -> {
                        _uiState.update { it.copy(isLoggingOut = true, error = null) }
                    }
                    is BaseResult.Success -> {
                        _uiState.update {
                            it.copy(isLoggingOut = false, isLogoutSuccessful = true, error = null)
                        }
                    }
                    is BaseResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoggingOut = false,
                                error = R.string.logout_failed
                            )
                        }
                    }
                }
            }
        }
    }
}
