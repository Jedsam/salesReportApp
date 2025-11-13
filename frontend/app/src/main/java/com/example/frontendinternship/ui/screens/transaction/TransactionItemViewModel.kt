package com.example.frontendinternship.ui.screens.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionItemViewModel @Inject constructor(
    private val loadTransactionItemsUseCase: ILoadTransactionItemsUseCase,
) :
    ViewModel() {
    data class TransactionItemUiState(
        var transactionWithItemModel: TransactionWithItemModel = TransactionWithItemModel()
    )

    private val _uiState = MutableStateFlow(TransactionItemUiState())
    val uiState: StateFlow<TransactionItemUiState> = _uiState.asStateFlow()

    fun loadAllTransactionItems(transactionId: String) {
        viewModelScope.launch {
            val transaction =
                async<TransactionWithItemModel> { loadTransactionItemsUseCase(transactionId) }
            _uiState.update { currentState ->
                currentState.copy(
                    transactionWithItemModel = transaction.await(),
                )
            }
        }
    }
}