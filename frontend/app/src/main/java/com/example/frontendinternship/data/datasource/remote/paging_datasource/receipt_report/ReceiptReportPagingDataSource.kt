package com.example.frontendinternship.data.datasource.remote.paging_datasource.receipt_report

import com.example.frontendinternship.data.datasource.remote.ApiService
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ReceiptReportPagingDataSource @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun sendPost(
        xmlBody: RequestBody
    ): Response<Unit> {
        return apiService.sendXmlReport(xmlBody = xmlBody)
    }
}