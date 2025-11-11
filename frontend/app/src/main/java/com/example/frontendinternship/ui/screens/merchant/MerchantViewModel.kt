package com.example.frontendinternship.ui.screens.merchant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.domain.usecase.merchant.ILoadMerchantsUseCase
import com.example.frontendinternship.domain.usecase.merchant.IUpdateMerchantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MerchantViewModel @Inject constructor(
    private val loadMerchantsUseCase: ILoadMerchantsUseCase,
    private val updateMerchantUseCase: IUpdateMerchantUseCase,
) :
    ViewModel() {
    data class MerchantUiState(
        val currentMerchant: MerchantModel = MerchantModel()
    )

    private val _uiState = MutableStateFlow(MerchantUiState())
    val uiState: StateFlow<MerchantUiState> = _uiState.asStateFlow()

    init {
        loadMerchant()
    }

    private fun loadMerchant() {
        viewModelScope.launch {
            val merchants = loadMerchantsUseCase()
            var merchantModel: MerchantModel
            if (merchants.size > 0) {
                merchantModel = merchants[0]
            } else {
                merchantModel = MerchantModel()
            }
            _uiState.update { currentState ->
                currentState.copy(currentMerchant = merchantModel)
            }
        }
    }

    fun updateMerchantName(merchantName: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(name = merchantName))
        }
    }

    fun updateBusinessName(businessName: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(businessName = businessName))
        }
    }

    fun updateMerchantAddress(merchantAddress: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(address = merchantAddress))
        }
    }

    fun updatePhone(phone: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(phone = phone))
        }
    }


    fun updateMerchant() {
        viewModelScope.launch {
            updateMerchantUseCase(uiState.value.currentMerchant)
        }
    }
}