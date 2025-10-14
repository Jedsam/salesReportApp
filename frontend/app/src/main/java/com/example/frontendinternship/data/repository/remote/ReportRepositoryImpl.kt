package com.example.frontendinternship.data.repository.remote

import com.example.frontendinternship.data.datasource.remote.paging_datasource.receipt_report.ReceiptReportPagingDataSource
import com.example.frontendinternship.domain.repository.ReportRepository
import jakarta.inject.Inject
import okhttp3.RequestBody.Companion.toRequestBody

class ReportRepositoryImpl @Inject constructor(
    private val receiptReportPagingDataSource: ReceiptReportPagingDataSource
) : ReportRepository {
    override suspend fun sendReport(xmlReportString: String): Boolean {
        val result =
            receiptReportPagingDataSource.sendPost(xmlBody = xmlReportString.toRequestBody())
        return result.isSuccessful
    }
}
