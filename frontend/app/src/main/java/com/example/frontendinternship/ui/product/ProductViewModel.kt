package com.example.frontendinternship.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.usecase.LoadProductsByVatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val loadProductsByVatUseCase: LoadProductsByVatUseCase) : ViewModel(){
    data class ProductGroup(
        val plu0: List<Product> = emptyList(),
        val plu1: List<Product> = emptyList(),
        val plu10: List<Product> = emptyList(),
        val plu20: List<Product> = emptyList(),
    //    val isLoading: Boolean = true
    )
    private val _products = MutableStateFlow(ProductGroup())
    val products: StateFlow<ProductGroup> = _products.asStateFlow()

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

            _products.update {
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
