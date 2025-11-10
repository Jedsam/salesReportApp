package com.example.frontendinternship.ui.screens.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import com.example.frontendinternship.utils.ProductOperationEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
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

    fun updateProductChanges(
        currentProduct: ProductModel,
        productOperation: ProductOperationEnum
    ) {
        if (productOperation == ProductOperationEnum.CANCELED) {
            return
        }
        _uiState.update { currentState ->
            val newProductList = currentState.productList.toMutableList()
            when (productOperation) {
                ProductOperationEnum.ADD -> {
                    newProductList.add(currentProduct.copy())
                }

                ProductOperationEnum.EDIT -> {
                    val index =
                        newProductList.indexOfFirst { it.productId == currentProduct.productId }
                    if (index != -1) {
                        newProductList[index] = currentProduct.copy()
                    }
                }

                ProductOperationEnum.DELETE -> {
                    newProductList.removeIf { it.productId == currentProduct.productId }
                }

                ProductOperationEnum.CANCELED -> {
                }
            }
            currentState.copy(
                productList = newProductList
            )
        }
    }
}