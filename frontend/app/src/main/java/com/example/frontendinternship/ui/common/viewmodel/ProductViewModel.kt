package com.example.frontendinternship.ui.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.utils.ProductOperationEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    // private val loadProductsUseCase: ILoadProductsUseCase
) :
    ViewModel() {
    data class ProductUiState(
        var currentProduct: ProductModel = ProductModel(),
        var productOperation: ProductOperationEnum = ProductOperationEnum.CANCELED,
        // var catalogProductList: List<ProductWithCount> = emptyList(),
    )

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun resetProduct() {
        _uiState.update { currentState ->
            currentState.copy(
                currentProduct = ProductModel(),
                productOperation = ProductOperationEnum.CANCELED,
            )
        }
    }

    fun updateProduct(product: ProductModel) {
        _uiState.update { currentState ->
            currentState.copy(currentProduct = product)
        }
    }

    fun chooseAddOperation() {
        _uiState.update { currentState ->
            currentState.copy(
                productOperation = ProductOperationEnum.ADD
            )
        }
    }

    fun chooseEditOperation() {
        _uiState.update { currentState ->
            currentState.copy(
                productOperation = ProductOperationEnum.EDIT
            )
        }
    }

    fun chooseDeleteOperation() {
        _uiState.update { currentState ->
            currentState.copy(
                productOperation = ProductOperationEnum.DELETE
            )
        }
    }

    fun updateProductName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentProduct = currentState.currentProduct.copy(productName = newName)
            )
        }
    }
    fun updatePrice(newPrice: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentProduct = currentState.currentProduct.copy(price = newPrice.toDoubleOrNull() ?: 0.0)
            )
        }
    }
    fun updateVatRate(newVatRate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentProduct = currentState.currentProduct.copy(vatRate = newVatRate.toDoubleOrNull() ?: 0.0)
            )
        }
    }
}