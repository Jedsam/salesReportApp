package com.example.frontendinternship.domain.usecase.iface

import com.example.frontendinternship.domain.model.UserModel

interface ILoginUseCase {
    suspend operator fun invoke(userModel: UserModel): Boolean
}
