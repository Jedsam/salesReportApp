package com.example.frontendinternship.domain.usecase.authentication

import com.example.frontendinternship.domain.model.UserModel

interface ILoginUseCase {
    suspend operator fun invoke(userModel: UserModel): Boolean
}
