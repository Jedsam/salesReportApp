package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.MerchantDao
import com.example.frontendinternship.data.datasource.local.mapper.toData
import com.example.frontendinternship.data.datasource.local.mapper.toDomain
import com.example.frontendinternship.data.model.MerchantEntity
import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.domain.repository.MerchantRepository
import com.example.frontendinternship.utils.toUUID
import jakarta.inject.Inject
import java.util.UUID

class MerchantRepositoryImpl @Inject constructor(
    private val merchantDao: MerchantDao
) : MerchantRepository {

    override suspend fun getCurrentMerchantID(): UUID {
        return merchantDao.getCurrent().merchantId.toUUID()
    }

    override suspend fun loadMerchants(): List<MerchantModel> {
        return merchantDao.getAll().map { merchantEntity: MerchantEntity ->
            merchantEntity.toDomain()
        }
    }


    override suspend fun updateMerchant(userId: UUID, merchantModel: MerchantModel) {
        merchantDao.editOne(merchantModel.toData(userId))
    }
}

