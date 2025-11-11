package com.example.frontendinternship.ui.screens.merchant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.domain.usecase.shop.ILoadMerchantsUseCase
import com.example.frontendinternship.domain.usecase.shop.IUpdateMerchantUseCase
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
            val shops = loadMerchantsUseCase()
            var shopModel: MerchantModel
            if (shops.size > 0) {
                shopModel = shops[0]
            } else {
                shopModel = MerchantModel()
            }
            _uiState.update { currentState ->
                currentState.copy(currentMerchant = shopModel)
            }
        }
    }

    fun updateMerchantName(shopName: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(name = shopName))
        }
    }

    fun updateMerchantAddress(shopAddress: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(address = shopAddress))
        }
    }

    fun updatePhone(phone: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(phone = phone))
        }
    }

    fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(currentMerchant = currentState.currentMerchant.copy(email = email))
        }
    }

    fun updateMerchant() {
        viewModelScope.launch {
            updateMerchantUseCase(uiState.value.currentMerchant)
        }
    }
}