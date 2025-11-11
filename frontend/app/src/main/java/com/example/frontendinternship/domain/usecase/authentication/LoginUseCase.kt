package com.example.frontendinternship.domain.usecase.authentication

import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : ILoginUseCase {
    override suspend fun invoke(userModel: UserModel): Boolean {
        return authRepository.login(userModel)
    }
}