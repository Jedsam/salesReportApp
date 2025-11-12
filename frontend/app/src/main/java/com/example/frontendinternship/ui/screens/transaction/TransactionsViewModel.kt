package com.example.frontendinternship.ui.screens.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val loadTransactionsUseCase: ILoadTransactionsUseCase
) :
    ViewModel() {
    data class TransactionUiState(
        var transactionList: List<TransactionModel> = emptyList(),
        var isLoggedIn: Boolean = false,
    )

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    init {
        loadAllTransactions()
    }

    private fun loadAllTransactions() {
        viewModelScope.launch {
            val productList = loadTransactionsUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    transactionList = productList
                )
            }
        }
    }

}