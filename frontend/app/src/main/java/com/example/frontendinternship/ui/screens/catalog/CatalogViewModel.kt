package com.example.frontendinternship.ui.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val loadProductsUseCase: ILoadProductsUseCase
) :
    ViewModel() {
    data class ProductUiState(
        var productList: List<ProductModel> = emptyList(),
        var isLoggedIn: Boolean = false,
    )

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        loadAllProducts()
        updateLoginState()
        // startReportCheckLoop()
    }

    fun updateLoginState() {
        _uiState.update { currentState ->
            currentState.copy(
                isLoggedIn = false
                // isLoggedIn = Math.random() > 0.5
            )
        }
    }

    fun closeOperatingWindow() {

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