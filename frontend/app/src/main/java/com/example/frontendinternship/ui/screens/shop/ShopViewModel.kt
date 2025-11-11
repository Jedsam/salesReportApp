package com.example.frontendinternship.ui.screens.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.ShopModel
import com.example.frontendinternship.domain.usecase.shop.ILoadShopsUseCase
import com.example.frontendinternship.domain.usecase.shop.IUpdateShopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val loadShopsUseCase: ILoadShopsUseCase,
    private val updateShopUseCase: IUpdateShopUseCase,
) :
    ViewModel() {
    data class ShopUiState(
        val currentShop: ShopModel = ShopModel()
    )

    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    init {
        loadShop()
    }

    private fun loadShop() {
        viewModelScope.launch {
            val shops = loadShopsUseCase()
            var shopModel: ShopModel
            if (shops.size > 0) {
                shopModel = shops[0]
            } else {
                shopModel = ShopModel()
            }
            _uiState.update { currentState ->
                currentState.copy(currentShop = shopModel)
            }
        }
    }

    fun updateShopName(shopName: String) {
        _uiState.update { currentState ->
            currentState.copy(currentShop = currentState.currentShop.copy(name = shopName))
        }
    }

    fun updateShopAddress(shopAddress: String) {
        _uiState.update { currentState ->
            currentState.copy(currentShop = currentState.currentShop.copy(address = shopAddress))
        }
    }

    fun updatePhone(phone: String) {
        _uiState.update { currentState ->
            currentState.copy(currentShop = currentState.currentShop.copy(phone = phone))
        }
    }

    fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(currentShop = currentState.currentShop.copy(email = email))
        }
    }
    fun updateShop() {
        viewModelScope.launch {
            updateShopUseCase(uiState.value.currentShop)
        }
    }
}