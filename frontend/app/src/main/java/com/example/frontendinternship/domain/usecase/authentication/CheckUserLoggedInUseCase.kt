package com.example.frontendinternship.domain.usecase.authentication

import com.example.frontendinternship.domain.repository.UserRepository
import javax.inject.Inject

class CheckUserLoggedInUseCase @Inject constructor(
    private var userRepository: UserRepository
) : ICheckUserLoggedInUseCase {
    override suspend fun invoke(): Boolean {
        return userRepository.checkIfTokenNotEmpty()
    }
}

