package com.example.frontendinternship.domain.usecase

import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.repository.ReportRepository
import javax.inject.Inject

class PostXmlReportUseCase
@Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend operator fun invoke(
        xmlReportString: String,
        connectionStatusString: String,
        connectedIpString: String
    ): Boolean {
        return reportRepository.sendReport(xmlReportString = xmlReportString)
    }
}