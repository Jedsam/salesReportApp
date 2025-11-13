package com.example.frontendinternship.domain.di

import com.example.frontendinternship.domain.usecase.authentication.CheckUserLoggedInUseCase
import com.example.frontendinternship.domain.usecase.authentication.ICheckUserLoggedInUseCase
import com.example.frontendinternship.domain.usecase.authentication.ILogOutUseCase
import com.example.frontendinternship.domain.usecase.product.ILoadProductsUseCase
import com.example.frontendinternship.domain.usecase.authentication.ILoginUseCase
import com.example.frontendinternship.domain.usecase.authentication.LogOutUseCase
import com.example.frontendinternship.domain.usecase.product.LoadProductsUseCase
import com.example.frontendinternship.domain.usecase.authentication.LoginUseCase
import com.example.frontendinternship.domain.usecase.merchant.ILoadMerchantsUseCase
import com.example.frontendinternship.domain.usecase.merchant.IUpdateMerchantUseCase
import com.example.frontendinternship.domain.usecase.merchant.LoadMerchantsUseCase
import com.example.frontendinternship.domain.usecase.merchant.UpdateMerchantUseCase
import com.example.frontendinternship.domain.usecase.product.AddProductsUseCase
import com.example.frontendinternship.domain.usecase.product.EditProductsUseCase
import com.example.frontendinternship.domain.usecase.product.IAddProductsUseCase
import com.example.frontendinternship.domain.usecase.product.IEditProductsUseCase
import com.example.frontendinternship.domain.usecase.product.IRemoveProductsUseCase
import com.example.frontendinternship.domain.usecase.product.RemoveProductsUseCase
import com.example.frontendinternship.domain.usecase.shop.ILoadShopsUseCase
import com.example.frontendinternship.domain.usecase.shop.IUpdateShopUseCase
import com.example.frontendinternship.domain.usecase.shop.LoadShopsUseCase
import com.example.frontendinternship.domain.usecase.shop.UpdateShopUseCase
import com.example.frontendinternship.domain.usecase.transaction.CreateCancelTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.CreateCashTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.CreateCouponTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.CreateCreditTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ICreateCancelTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ICreateCashTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ICreateCouponTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ICreateCreditTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionItemsUseCase
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.ILoadTransactionsUseCase
import com.example.frontendinternship.domain.usecase.transaction.ISynchronizeTransactionsUseCase
import com.example.frontendinternship.domain.usecase.transaction.LoadTransactionItemsUseCase
import com.example.frontendinternship.domain.usecase.transaction.LoadTransactionUseCase
import com.example.frontendinternship.domain.usecase.transaction.LoadTransactionsUseCase
import com.example.frontendinternship.domain.usecase.transaction.SynchronizeTransactionsUseCase
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

    @Binds
    abstract fun bindAddProductsUseCase(
        impl: AddProductsUseCase
    ): IAddProductsUseCase

    @Binds
    abstract fun bindEditProductsUseCase(
        impl: EditProductsUseCase
    ): IEditProductsUseCase

    @Binds
    abstract fun bindRemoveProductsUseCase(
        impl: RemoveProductsUseCase
    ): IRemoveProductsUseCase

    @Binds
    abstract fun bindLoadShopsUseCase(
        impl: LoadShopsUseCase
    ): ILoadShopsUseCase

    @Binds
    abstract fun bindUpdateShopUseCase(
        impl: UpdateShopUseCase
    ): IUpdateShopUseCase

    @Binds
    abstract fun bindLoadMerchantsUseCase(
        impl: LoadMerchantsUseCase
    ): ILoadMerchantsUseCase

    @Binds
    abstract fun bindUpdateMerchantUseCase(
        impl: UpdateMerchantUseCase
    ): IUpdateMerchantUseCase

    @Binds
    abstract fun bindLoadTransactionsUseCase(
        impl: LoadTransactionsUseCase
    ): ILoadTransactionsUseCase

    @Binds
    abstract fun bindLoadTransactionUseCase(
        impl: LoadTransactionUseCase
    ): ILoadTransactionUseCase

    @Binds
    abstract fun bindLoadTransactionItemsUseCase(
        impl: LoadTransactionItemsUseCase
    ): ILoadTransactionItemsUseCase

    @Binds
    abstract fun bindCheckUserLoggedInUseCase(
        impl: CheckUserLoggedInUseCase
    ): ICheckUserLoggedInUseCase

    @Binds
    abstract fun bindSynchronizeTransactionUseCase(
        impl: SynchronizeTransactionsUseCase
    ): ISynchronizeTransactionsUseCase

    @Binds
    abstract fun bindCreateCashTransactionUseCase(
        impl: CreateCashTransactionUseCase
    ): ICreateCashTransactionUseCase

    @Binds
    abstract fun bindCreateCouponTransactionUseCase(
        impl: CreateCouponTransactionUseCase
    ): ICreateCouponTransactionUseCase

    @Binds
    abstract fun bindCreateCreditTransactionUseCase(
        impl: CreateCreditTransactionUseCase
    ): ICreateCreditTransactionUseCase

    @Binds
    abstract fun bindCreateCancelTransactionUseCase(
        impl: CreateCancelTransactionUseCase
    ): ICreateCancelTransactionUseCase
    @Binds
    abstract fun bindLogOutUseCase(
        impl: LogOutUseCase
    ): ILogOutUseCase
}