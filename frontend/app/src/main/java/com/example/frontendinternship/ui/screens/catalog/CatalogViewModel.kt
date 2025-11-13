package com.example.frontendinternship.ui.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.usecase.authentication.ICheckUserLoggedInUseCase
import com.example.frontendinternship.domain.usecase.authentication.ILogOutUseCase
import com.example.frontendinternship.domain.usecase.product.IAddProductsUseCase
import com.example.frontendinternship.domain.usecase.product.IEditProductsUseCase
import com.example.frontendinternship.domain.usecase.product.ILoadProductsUseCase
import com.example.frontendinternship.domain.usecase.product.IRemoveProductsUseCase
import com.example.frontendinternship.utils.ProductOperationEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val loadProductsUseCase: ILoadProductsUseCase,
    private val addProductsUseCase: IAddProductsUseCase,
    private val editProductsUseCase: IEditProductsUseCase,
    private val removeProductsUseCase: IRemoveProductsUseCase,
    private val checkUserLoggedInUseCase: ICheckUserLoggedInUseCase,
    private val logOutUseCase: ILogOutUseCase,
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

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    isLoggedIn = false
                )
            }
        }
    }

    fun updateLoginState() {
        viewModelScope.launch {
            val isLoggedIn = checkUserLoggedInUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    isLoggedIn = isLoggedIn
                )
            }
        }
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

        viewModelScope.launch {
            _uiState.update { currentState ->
                val newProductList = currentState.productList.toMutableList()
                when (productOperation) {
                    ProductOperationEnum.ADD -> {
                        val productCopy = currentProduct.copy()
                        newProductList.add(productCopy)
                        addProductsUseCase(listOf(productCopy))
                    }

                    ProductOperationEnum.EDIT -> {
                        val index =
                            newProductList.indexOfFirst { it.productId == currentProduct.productId }
                        if (index != -1) {
                            val productCopy = currentProduct.copy()
                            newProductList[index] = productCopy
                            editProductsUseCase(listOf(productCopy))
                        }
                    }

                    ProductOperationEnum.DELETE -> {
                        val productID = currentProduct.productId
                        if (productID != null) {
                            newProductList.removeIf { it.productId == currentProduct.productId }
                            removeProductsUseCase(currentProduct.productId)
                        }
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
}