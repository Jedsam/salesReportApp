package com.example.frontendinternship.ui.screens.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.domain.model.getTax
import com.example.frontendinternship.domain.usecase.transaction.ICreateCancelTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ICreateCashTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ICreateCouponTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ICreateCreditTransactionUseCase
import com.example.frontendinternship.ui.states.CashPaymentState
import com.example.frontendinternship.ui.states.CouponPaymentState
import com.example.frontendinternship.ui.states.CreditPaymentState
import com.example.frontendinternship.ui.states.PaymentState
import com.example.frontendinternship.utils.PaymentTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val createCancelTransactionUseCase: ICreateCancelTransactionUseCase,
    private val createCashTransactionUseCase: ICreateCashTransactionUseCase,
    private val createCouponTransactionUseCase: ICreateCouponTransactionUseCase,
    private val createCreditTransactionUseCase: ICreateCreditTransactionUseCase,
) :
    ViewModel() {
    data class PaymentUiState(
        var payment: PaymentState = PaymentState(),
        var cashPayment: CashPaymentState = CashPaymentState(),
        var creditPayment: CreditPaymentState = CreditPaymentState(),
        var couponPayment: CouponPaymentState = CouponPaymentState(),
    )

    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    fun startPayment(basketList: List<ProductWithCount>) {
        var subtotal = 0.0
        var total = 0.0
        var currCost: Double
        for (product in basketList) {
            currCost = product.getCost()
            subtotal += currCost - product.getTax()
            total += currCost
        }
        _uiState.update { currentState ->
            currentState.copy(
                payment =
                    PaymentState(
                        subtotal = subtotal,
                        total = total,
                    )
            )
        }
    }

    fun cancelPayment(productBasket: List<ProductWithCount>) {
        viewModelScope.launch {
            createCancelTransactionUseCase(
                uiState.value.payment.copy(paymentType = PaymentTypeEnum.CANCEL),
                productBasket
            )
        }
    }

    fun chargePayment(productBasket: List<ProductWithCount>) {
        viewModelScope.launch {
            when (uiState.value.payment.paymentType) {
                PaymentTypeEnum.CASH ->
                    createCashTransactionUseCase(
                        uiState.value.payment,
                        uiState.value.cashPayment,
                        productBasket
                    )

                PaymentTypeEnum.CREDIT ->
                    createCreditTransactionUseCase(
                        uiState.value.payment,
                        uiState.value.creditPayment,
                        productBasket
                    )

                PaymentTypeEnum.COUPON ->
                    createCouponTransactionUseCase(
                        uiState.value.payment,
                        uiState.value.couponPayment,
                        productBasket
                    )

                PaymentTypeEnum.CANCEL -> {

                }
            }
        }
    }

    fun changeToCashPayment() {
        _uiState.update { currentState ->
            currentState.copy(payment = currentState.payment.copy(paymentType = PaymentTypeEnum.CASH))
        }
    }

    fun changeToCreditPayment() {
        _uiState.update { currentState ->
            currentState.copy(payment = currentState.payment.copy(paymentType = PaymentTypeEnum.CREDIT))
        }
    }

    fun changeToCouponPayment() {
        _uiState.update { currentState ->
            currentState.copy(payment = currentState.payment.copy(paymentType = PaymentTypeEnum.COUPON))
        }
    }


    fun updateCardNumber(cardNumber: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(creditPayment = currentState.creditPayment.copy(cardNumber = cardNumber))
            }
        }
    }

    fun updateExpirationDate(expirationDate: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(creditPayment = currentState.creditPayment.copy(expirationDate = expirationDate))
            }
        }
    }

    fun updateCVV(cvv: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(creditPayment = currentState.creditPayment.copy(cvv = cvv))
            }
        }
    }

    fun updateCouponCode(couponCode: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(couponPayment = currentState.couponPayment.copy(couponCode = couponCode))
            }
        }
    }

    fun updateCouponValue(couponValue: Double) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(couponPayment = currentState.couponPayment.copy(couponValue = couponValue))
            }
        }
    }

    fun updateExpiryDate(expiryDate: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(couponPayment = currentState.couponPayment.copy(expiryDate = expiryDate))
            }
        }
    }

    fun updateCashAmount(receivedAmount: Double) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(cashPayment = currentState.cashPayment.copy(receivedAmount = receivedAmount))
            }
        }
    }
}