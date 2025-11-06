package com.example.frontendinternship.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductEditViewModel @Inject constructor() :
    ViewModel() {
    data class ProductUiState(
        var switchCounter: Int = 0,
        val currentProduct: ProductModel = ProductModel()
    )

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun incrementCounter() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(switchCounter = currentState.switchCounter + 1)
            }
        }
    }
}