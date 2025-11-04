package com.example.frontendinternship.ui.screens.register

import androidx.lifecycle.ViewModel
import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.ui.states.MerchantRegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() :
    ViewModel() {
    data class RegisterUiState(
        val currentUser: MerchantRegisterState = MerchantRegisterState()
    )
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()
}