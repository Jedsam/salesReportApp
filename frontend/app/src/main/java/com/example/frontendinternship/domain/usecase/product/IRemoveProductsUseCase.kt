package com.example.frontendinternship.domain.usecase.product

import java.util.UUID

interface IRemoveProductsUseCase {
    suspend operator fun invoke(uuid: UUID)
}
