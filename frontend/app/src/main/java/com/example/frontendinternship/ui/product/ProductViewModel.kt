package com.example.frontendinternship.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.domain.usecase.LoadProductsByVatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val loadProductsByVatUseCase: LoadProductsByVatUseCase) :
    ViewModel() {
    data class ProductUiState(
        val plu0: List<Product> = emptyList(),
        val plu1: List<Product> = emptyList(),
        val plu10: List<Product> = emptyList(),
        val plu20: List<Product> = emptyList(),
        val basketList: List<ProductWithCount> = emptyList(),
        val totalBasketPrice: Float = 0f,
        val isConfirmationWindowOpen: Boolean = false,
        // Confirmation window states
        val confirmationProduct: ProductWithCount? = null,
        val confirmationCost: Float = 0f,
        val confirmationQuantityInput: String = "1",
        val confirmationPriceInput: String = "",
    )

    fun onConfirmationDismissed() {
        _uiState.update { currentState ->
            currentState.copy(
                isConfirmationWindowOpen = false,
                confirmationProduct = null,
                confirmationQuantityInput = "1",
                confirmationPriceInput = ""
            )
        }
    }

    fun onQuantityFieldValueChanged(newText: String) {
        _uiState.update { currentState ->

            var quantityValue = currentState.confirmationQuantityInput
            var currentProduct = currentState.confirmationProduct
            var currentCost = currentState.confirmationCost

            val tempText = newText.filter { it.isDigit() }
            val textInt = tempText.toIntOrNull() ?: 0
            if (textInt <= 99 && textInt >= 1) {
                quantityValue = tempText
                currentProduct = currentProduct?.copy(count = textInt)
                currentCost = currentProduct?.getCost() ?: 0f
            }
            currentState.copy(
                confirmationProduct = currentProduct,
                confirmationCost = currentCost,
                confirmationQuantityInput = quantityValue
            )
        }
    }

    fun onPriceFieldValueChanged(newText: String) {
        _uiState.update { currentState ->
            var priceText = currentState.confirmationPriceInput
            var currentProduct = currentState.confirmationProduct
            var currentCost = currentState.confirmationCost

            val tempText = newText.filter { it.isDigit() }
            val textFloat = tempText.toFloatOrNull() ?: 0f
            if (textFloat <= 999.99 && textFloat >= 0.01) {
                priceText = tempText
                currentProduct =
                    currentProduct?.product?.copy(price = textFloat)?.let {
                        currentProduct.copy(
                            product = it
                        )
                    }
                currentCost = currentProduct?.getCost() ?: 0f
            }
            currentState.copy(
                confirmationProduct = currentProduct,
                confirmationCost = currentCost,
                confirmationPriceInput = priceText
            )
        }
    }

    fun onConfirmClick() {
        _uiState.update { currentState ->
            // Save the item on the shop list
            currentState.confirmationProduct?.let { product ->
                val basketList = currentState.basketList + product
                val totalBasketPrice = currentState.totalBasketPrice + currentState.confirmationCost
                return@update currentState.copy(
                    basketList = basketList,
                    totalBasketPrice = totalBasketPrice,
                    isConfirmationWindowOpen = false,
                )
            }
            currentState.copy(
                isConfirmationWindowOpen = false,
            )

        }
    }

    fun onProductSelect(selectedProduct: Product) {
        _uiState.update { currentState ->
            val currentProduct = ProductWithCount(product = selectedProduct.copy(), 1)
            val currentCost = currentProduct.getCost()

            currentState.copy(
                confirmationProduct = currentProduct,
                confirmationCost = currentCost,
                // Set confirmation values
                confirmationPriceInput = currentProduct.product.price.toString(),
                confirmationQuantityInput = "1",
                isConfirmationWindowOpen = true,
            )
        }
    }

    fun onActionButtonClick(paymentMethod: PAYMENT_METHOD) {
        _uiState.update { currentState ->
            //receiptDao?.insert(basketList, paymentMethod)
            //reportReceipt?.checkAndReportBasket(basketList)

            currentState.copy(
                basketList = emptyList(),
                totalBasketPrice = 0f
            )
        }
    }

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        loadAllProducts()
    }

    private fun loadAllProducts() {
        viewModelScope.launch {
            // Data loading is now centralized here, off the Main Thread
            val p0 = loadProductsByVatUseCase(0)
            val p1 = loadProductsByVatUseCase(1)
            val p10 = loadProductsByVatUseCase(10)
            val p20 = loadProductsByVatUseCase(20)

            _uiState.update {
                it.copy(
                    plu0 = p0,
                    plu1 = p1,
                    plu10 = p10,
                    plu20 = p20,
                    // isLoading = false
                )
            }
        }
    }
}
