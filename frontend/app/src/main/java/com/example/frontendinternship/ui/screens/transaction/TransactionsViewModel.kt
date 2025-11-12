package com.example.frontendinternship.ui.screens.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.usecase.authentication.ICheckUserLoggedInUseCase
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionsUseCase
import com.example.frontendinternship.domain.usecase.transaction.ISynchronizeTransactionsUseCase
import com.example.frontendinternship.utils.APIOperationStateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val loadTransactionsUseCase: ILoadTransactionsUseCase,
    private val synchronizeTransactionsUseCase: ISynchronizeTransactionsUseCase,
    private val checkUserLoggedInUseCase: ICheckUserLoggedInUseCase
) :
    ViewModel() {
    data class TransactionUiState(
        var transactionList: List<TransactionModel> = emptyList(),
        var syncResult: APIOperationStateEnum = APIOperationStateEnum.READY,
        var isLoggedIn: Boolean = false,
    )

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    init {
        loadAllTransactions()
        checkIfLoggedIn()
    }

    private fun checkIfLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = checkUserLoggedInUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    isLoggedIn = isLoggedIn
                )
            }
        }
    }

    fun synchronizeTransactions() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    syncResult = APIOperationStateEnum.EXECUTING
                )
            }
            _uiState.update { currentState ->
                currentState.copy(
                    syncResult = if (synchronizeTransactionsUseCase())
                        APIOperationStateEnum.SUCCESS
                    else APIOperationStateEnum.FAILURE
                )
            }
            delay(5000) // wait for 5 seconds till disabling it
            _uiState.update { currentState ->
                currentState.copy(
                    syncResult = APIOperationStateEnum.READY
                )
            }
        }
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