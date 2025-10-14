package com.example.frontendinternship.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.domain.usecase.FinalizeOrderUseCase
import com.example.frontendinternship.domain.usecase.LoadProductsByVatUseCase
import com.example.frontendinternship.domain.usecase.LoadReceiptByDateUseCase
import com.example.frontendinternship.domain.usecase.PostXmlReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val loadProductsByVatUseCase: LoadProductsByVatUseCase,
    private val finalizeOrderUseCase: FinalizeOrderUseCase,
    private val postXmlReportUseCase: PostXmlReportUseCase,
    private val loadReceiptByDateUseCase: LoadReceiptByDateUseCase
) :
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
        var connectedIpString: String = "Not connected",
        var connectionStatusString: String = ""
    )

    private var oldTime: Long = System.currentTimeMillis() - 60 * 60 * 1000 // 3,600,000 ms
    private var currentTime: Long = System.currentTimeMillis()

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
            finalizeOrderUseCase(
                basketList = currentState.basketList,
                paymentMethod = paymentMethod
            )
            val newBasketList = emptyList<ProductWithCount>()
            checkAndReportBasket(
                basketList = newBasketList,
                connectedIpString = currentState.connectedIpString,
                connectionStatusString = currentState.connectionStatusString
            )

            currentState.copy(
                basketList = newBasketList,
                totalBasketPrice = 0f
            )
        }
    }

    private fun checkAndReportBasket(
        basketList: List<ProductWithCount>,
        connectionStatusString: String,
        connectedIpString: String
    ) {
        currentTime = System.currentTimeMillis()
        val oneHourMillis = 60 * 60 * 1000 // 3,600,000 ms
        if (currentTime - oldTime >= oneHourMillis) {
            if (basketList.isEmpty()) {
                val xml = getXmlReport()
                oldTime =
                    (currentTime / oneHourMillis) * oneHourMillis // reset to the start of the current hour
                viewModelScope.launch {
                    val result = postXmlReportUseCase(
                        xmlReportString = xml,
                        connectedIpString = _uiState.value.connectedIpString,
                        connectionStatusString = _uiState.value.connectionStatusString
                    )
                    _uiState.update { currentState ->
                        currentState.copy(
                            connectedIpString = if (result) "Connected" else "Failed to connect",
                            connectionStatusString = if (result) "Successfully sent report!" else "Failed to send report!"
                        )
                    }
                    delay(timeMillis = 5_000) // 5 seconds
                    _uiState.update { currentState ->
                        currentState.copy(
                            connectionStatusString = ""
                        )
                    }
                }
            }
        }
    }

    private fun getXmlReport(): String {
        val date = Date(oldTime)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault())
        val dateTime = formatter.format(date)

        val receiptList = loadReceiptByDateUseCase(xmlReportString = dateTime)
        val receiptCount: Int = receiptList.size
        var totalAmount = 0f
        var canceledReceiptsCount = 0
        var canceledReceiptsAmount = 0f
        var vatRate0Amount = 0f
        var vatRate1Amount = 0f
        var vatRate10Amount = 0f
        var vatRate20Amount = 0f
        var cashPaymentAmount = 0f
        var creditPaymentAmount = 0f
        var couponPaymentAmount = 0f
        for (receipt in receiptList) {
            var currentTotalAmount = 0f
            receipt.paymentType.let {
                val isNotCancel =
                    if (PAYMENT_METHOD.entries[it] == PAYMENT_METHOD.CANCEL) 0f else 1f
                var currentVatRateAmount = receipt.amountVat0
                vatRate0Amount += currentVatRateAmount * isNotCancel
                currentTotalAmount += currentVatRateAmount
                currentVatRateAmount = receipt.amountVat1
                vatRate1Amount += currentVatRateAmount * isNotCancel
                currentTotalAmount += currentVatRateAmount
                currentVatRateAmount = receipt.amountVat10
                vatRate10Amount += currentVatRateAmount * isNotCancel
                currentTotalAmount += currentVatRateAmount
                currentVatRateAmount = receipt.amountVat20
                vatRate20Amount += currentVatRateAmount * isNotCancel
                currentTotalAmount += currentVatRateAmount
                when (PAYMENT_METHOD.entries[it]) {
                    PAYMENT_METHOD.CANCEL -> {
                        canceledReceiptsCount++
                        canceledReceiptsAmount += currentTotalAmount
                        currentTotalAmount = 0f
                    }

                    PAYMENT_METHOD.CASH -> {
                        cashPaymentAmount += currentTotalAmount
                    }

                    PAYMENT_METHOD.CREDIT -> {
                        creditPaymentAmount += currentTotalAmount
                    }

                    PAYMENT_METHOD.COUPON -> {
                        couponPaymentAmount += currentTotalAmount
                    }
                }
                totalAmount += currentTotalAmount
            }
        }
        // Convert the values to an xml string
        val xml = buildString {
            append("<hourlyReport>")
            append("<reportHour>$dateTime</reportHour>")
            append("<receiptCount>$receiptCount</receiptCount>")
            append("<totalAmount>$totalAmount</totalAmount>")
            append("<canceledInfo>")
            append("<count>$canceledReceiptsCount</count>")
            append("<amount>$canceledReceiptsAmount</amount>")
            append("</canceledInfo>")
            append("<salesVatDistribution>")
            append("<sales>")
            append("<vatRate>0</vatRate>")
            append("<amount>$vatRate0Amount</amount>")
            append("</sales>")
            append("<sales>")
            append("<vatRate>1</vatRate>")
            append("<amount>$vatRate1Amount</amount>")
            append("</sales>")
            append("<sales>")
            append("<vatRate>10</vatRate>")
            append("<amount>$vatRate10Amount</amount>")
            append("</sales>")
            append("<sales>")
            append("<vatRate>20</vatRate>")
            append("<amount>$vatRate20Amount</amount>")
            append("</sales>")
            append("</salesVatDistribution>")
            append("<paymentDistribution>")
            append("<payment>")
            append("<vatRate>Cash</vatRate>")
            append("<amount>$cashPaymentAmount</amount>")
            append("</payment>")
            append("<payment>")
            append("<vatRate>Credit</vatRate>")
            append("<amount>$creditPaymentAmount</amount>")
            append("</payment>")
            append("<payment>")
            append("<vatRate>Coupon</vatRate>")
            append("<amount>$couponPaymentAmount</amount>")
            append("</payment>")
            append("</paymentDistribution>")
            append("</hourlyReport>")
        }
        return xml
    }

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        loadAllProducts()
        startReportCheckLoop()
    }

    private fun loadAllProducts() {
        viewModelScope.launch {
            // Data loading is now centralized here, off the Main Thread
            val p0 = loadProductsByVatUseCase(0)
            val p1 = loadProductsByVatUseCase(1)
            val p10 = loadProductsByVatUseCase(10)
            val p20 = loadProductsByVatUseCase(20)

            _uiState.update { currentState ->
                currentState.copy(
                    plu0 = p0,
                    plu1 = p1,
                    plu10 = p10,
                    plu20 = p20,
                    // isLoading = false
                )
            }
        }
    }

    private fun startReportCheckLoop() {
        viewModelScope.launch {
            while (true) {
                _uiState.update { currentState ->
                    checkAndReportBasket(
                        basketList = currentState.basketList,
                        connectedIpString = currentState.connectedIpString,
                        connectionStatusString = currentState.connectionStatusString
                    )
                    currentState.copy()
                }
                delay(timeMillis = 60_000) // 60 seconds
            }
        }
    }
}
