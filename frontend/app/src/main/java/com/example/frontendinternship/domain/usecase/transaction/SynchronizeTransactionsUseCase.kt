package com.example.frontendinternship.domain.usecase.transaction

import android.icu.text.SimpleDateFormat
import com.example.frontendinternship.domain.repository.SynchronizeRepository
import com.example.frontendinternship.domain.repository.TransactionRepository
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.String

class SynchronizeTransactionsUseCase @Inject constructor(
    private val synchronizeRepository: SynchronizeRepository,
) : ISynchronizeTransactionsUseCase {
    private var lastSynchronization: Long = System.currentTimeMillis() - 60 * 60 * 1000

    override suspend fun invoke(forceSync: Boolean): Boolean {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastSynchronization > 36000 || forceSync) {
            val returnVal = synchronizeRepository.synchronize(
                SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(Date(lastSynchronization)),
            )
            if (returnVal) {
                lastSynchronization = currentTime
            }
            return true
        } else {
            return false
        }
    }
}