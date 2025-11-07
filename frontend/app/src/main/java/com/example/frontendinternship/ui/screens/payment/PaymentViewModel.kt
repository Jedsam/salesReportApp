package com.example.frontendinternship.ui.screens.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class PaymentViewModel @Inject constructor() :
    ViewModel() {
    data class PaymentUiState(
        var payment: PaymentState = PaymentState(),
        var cashPayment: CreditPaymentState = CreditPaymentState(),
        var creditPayment: CreditPaymentState = CreditPaymentState(),
        var couponPayment: CreditPaymentState = CreditPaymentState(),
    )

    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

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

    fun incrementCounter() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy()
            }
        }
    }
}