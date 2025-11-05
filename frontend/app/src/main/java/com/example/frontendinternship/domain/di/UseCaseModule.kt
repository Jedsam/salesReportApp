package com.example.frontendinternship.domain.di

import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import com.example.frontendinternship.domain.usecase.iface.ILoginUseCase
import com.example.frontendinternship.domain.usecase.implementation.LoadProductsUseCase
import com.example.frontendinternship.domain.usecase.implementation.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindLoadProductsUseCase(
        impl: LoadProductsUseCase
    ): ILoadProductsUseCase

    @Binds
    abstract fun bindLoginUseCase(
        impl: LoginUseCase
    ): ILoginUseCase
}