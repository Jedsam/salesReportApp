package com.example.frontendinternship.ui.screens.basket

import androidx.lifecycle.ViewModel
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ProductWithCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor() :
    ViewModel() {
    data class BasketUiState(
        var productBasket: List<ProductWithCount> = emptyList(),
        var lastAddedProduct: ProductWithCount = ProductWithCount(ProductModel(productName = ""), 0)
    )

    private val _uiState = MutableStateFlow(BasketUiState())
    val uiState: StateFlow<BasketUiState> = _uiState.asStateFlow()

    fun incrementProduct(productToIncrement: ProductWithCount) {
        val basket = uiState.value.productBasket

        val index =
            basket.indexOfFirst { it.product.productId == productToIncrement.product.productId }

        val incrementedValue = productToIncrement.count - 1
        if (index != -1 && incrementedValue < 999 && incrementedValue > 0) {
            _uiState.update { currentState ->
                val newBasket = currentState.productBasket.toMutableList()
                newBasket[index] = productToIncrement.copy(count = incrementedValue)
                currentState.copy(
                    productBasket = newBasket
                )
            }
        }
    }

    fun decrementProduct(productToIncrement: ProductWithCount) {
        val basket = uiState.value.productBasket

        val index =
            basket.indexOfFirst { it.product.productId == productToIncrement.product.productId }

        val decrementedValue = productToIncrement.count - 1
        if (index != -1 && decrementedValue < 999 && decrementedValue > 0) {
            _uiState.update { currentState ->
                val newBasket = currentState.productBasket.toMutableList()
                newBasket[index] = productToIncrement.copy(count = decrementedValue)
                currentState.copy(
                    productBasket = newBasket
                )
            }
        }
    }

    fun addToBasket(product: ProductModel) {
        val basket = uiState.value.productBasket

        val existingProduct = basket.find { it.product.productId == product.productId }

        if (existingProduct == null) {
            _uiState.update { currentState ->
                val newBasket = currentState.productBasket.toMutableList()
                val lastAddedProductWithCount = ProductWithCount(product, count = 1)
                newBasket.add(lastAddedProductWithCount)
                currentState.copy(
                    lastAddedProduct = lastAddedProductWithCount,
                    productBasket = newBasket
                )
            }
        }
    }
}