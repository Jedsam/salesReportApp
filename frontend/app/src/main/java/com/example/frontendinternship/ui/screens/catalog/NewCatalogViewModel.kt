package com.example.frontendinternship.ui.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.usecase.LoadProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCatalogViewModel @Inject constructor(
    private val loadProductsUseCase: LoadProductsUseCase
) :
    ViewModel() {
    data class ProductUiState(var productList: List<Product> = emptyList())

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        loadAllProducts()
        // startReportCheckLoop()
    }

    private fun loadAllProducts() {
        viewModelScope.launch {
            val productList = loadProductsUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    productList = productList
                )
            }
        }
    }
}