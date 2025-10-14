package com.example.frontendinternship.domain.repository


interface ReportRepository {
    suspend fun sendReport(xmlReportString: String): Boolean
}