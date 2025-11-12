package com.example.frontendinternship.domain.usecase.authentication


interface ICheckUserLoggedInUseCase {
    suspend operator fun invoke(): Boolean
}