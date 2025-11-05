package com.example.frontendinternship.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import com.example.frontendinternship.domain.usecase.iface.ILoginUseCase
import com.example.frontendinternship.domain.usecase.implementation.LoginUseCase
import com.example.frontendinternship.utils.OperationStateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase
) :
    ViewModel() {
    data class LoginUiState(
        val currentUser: UserModel = UserModel(),
        val loginLoadingState: OperationStateEnum = OperationStateEnum.DISABLED,
    )


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun closeOperatingWindow() {
        _uiState.update { currentState ->
            currentState.copy(
                loginLoadingState = OperationStateEnum.DISABLED
            )
        }
    }

    fun loginRequest() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    loginLoadingState = OperationStateEnum.EXECUTING
                )
            }
            val isConnected = loginUseCase(_uiState.value.currentUser)
            _uiState.update { currentState ->
                currentState.copy(
                    loginLoadingState =
                        if (isConnected) OperationStateEnum.SUCCESS else OperationStateEnum.FAILURE
                )
            }
            return@launch
        }
    }
}