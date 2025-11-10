package com.example.frontendinternship.ui.screens.shop

import androidx.lifecycle.ViewModel
import com.example.frontendinternship.ui.states.MerchantRegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor() :
    ViewModel() {
    data class RegisterUiState(
        val currentUser: MerchantRegisterState = MerchantRegisterState()
    )

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()
}