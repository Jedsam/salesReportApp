package com.example.frontendinternship.domain.usecase.implementation

import com.example.frontendinternship.data.repository.remote.AuthRepositoryImpl
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.domain.repository.AuthRepository
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import com.example.frontendinternship.domain.usecase.iface.ILoginUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : ILoginUseCase {
    override suspend fun invoke(userModel: UserModel): Boolean {
        return authRepository.login(userModel)
    }
}