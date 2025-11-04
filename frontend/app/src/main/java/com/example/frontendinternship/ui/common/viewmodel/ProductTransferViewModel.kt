package com.example.frontendinternship.ui.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.frontendinternship.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductTransferViewModel @Inject constructor(
    // private val loadProductsUseCase: ILoadProductsUseCase
) :
    ViewModel() {
    data class ProductUiState(
        var currentProduct: ProductModel = ProductModel(),
        // var catalogProductList: List<ProductWithCount> = emptyList(),
    )

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()


    fun updateProduct(product: ProductModel) {
        _uiState.update { currentState ->
            currentState.copy(currentProduct = product)
        }
    }

}