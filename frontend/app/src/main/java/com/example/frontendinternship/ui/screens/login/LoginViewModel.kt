package com.example.frontendinternship.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.domain.usecase.authentication.ILoginUseCase
import com.example.frontendinternship.utils.APIOperationStateEnum
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
        val loginLoadingState: APIOperationStateEnum = APIOperationStateEnum.DISABLED,
    )


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateUsername(newUsername: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentUser = currentState.currentUser.copy(username = newUsername)
            )
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentUser = currentState.currentUser.copy(password = newPassword)
            )
        }
    }

    fun closeOperatingWindow() {
        _uiState.update { currentState ->
            currentState.copy(
                loginLoadingState = APIOperationStateEnum.DISABLED
            )
        }
    }

    fun loginRequest() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    loginLoadingState = APIOperationStateEnum.EXECUTING
                )
            }
            val isConnected = loginUseCase(_uiState.value.currentUser)
            _uiState.update { currentState ->
                currentState.copy(
                    loginLoadingState =
                        if (isConnected) APIOperationStateEnum.SUCCESS else APIOperationStateEnum.FAILURE
                )
            }
            return@launch
        }
    }
}