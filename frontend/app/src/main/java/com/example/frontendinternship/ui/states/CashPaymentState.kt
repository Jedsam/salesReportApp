package com.example.frontendinternship.ui.states

data class CashPaymentState(
    var receivedAmount: Double = 0.0
) {
    fun calculateChange(cost: Double): Double {
        if (receivedAmount > cost) {
            return receivedAmount - cost
        }
        return 0.0
    }
}

