package com.example.frontendinternship.data.di

import com.example.frontendinternship.data.repository.local.ProductRepositoryImpl
import com.example.frontendinternship.data.repository.local.ReceiptRepositoryImpl
import com.example.frontendinternship.data.repository.local.ShopRepositoryImpl
import com.example.frontendinternship.data.repository.local.UserRepositoryImpl
import com.example.frontendinternship.data.repository.remote.AuthRepositoryImpl
import com.example.frontendinternship.domain.repository.AuthRepository
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.repository.ReceiptRepository
import com.example.frontendinternship.domain.repository.ReportRepository
import com.example.frontendinternship.domain.repository.ShopRepository
import com.example.frontendinternship.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindReceiptRepository(
        receiptRepositoryImpl: ReceiptRepositoryImpl
    ): ReceiptRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindShopRepository(
        shopRepositoryImpl: ShopRepositoryImpl
    ): ShopRepository
}