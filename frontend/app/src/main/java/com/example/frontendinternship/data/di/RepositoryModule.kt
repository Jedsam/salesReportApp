package com.example.frontendinternship.data.di
import com.example.frontendinternship.data.repository.local.ProductRepositoryImpl
import com.example.frontendinternship.data.repository.local.ReceiptRepositoryImpl
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.repository.ReceiptRepository
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
}