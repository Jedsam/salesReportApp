package com.example.frontendinternship

import androidx.lifecycle.ViewModel
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReportReceiptViewModel(
    private val receiptDao: ReceiptDao?
) : ViewModel() {
    private lateinit var connectionStatus: String
    private lateinit var url: URL
    private var oldTime: Long = System.currentTimeMillis()
    private var currentTime: Long = System.currentTimeMillis()
    init {
        Thread{
        val ip = "10.0.2.2"
        val port = 4478
        val path = "/receive"
        val urlString = "http://$ip:$port$path"
            url = URL(urlString)
            var connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            connection.connect()                // tries to reach server
            connectionStatus = ip
            connection.disconnect()
        } catch (e: Exception) {
            connectionStatus = "Not able to connect to the server!"
        }}.start()
    }
    fun getConnectionStatusString() :String {return connectionStatus}

    fun checkAndReportBasket(basketList: List<ProductWithCount>) {
        if (receiptDao == null) {
            error("Could not load receipt data base!")
        }

        val oneHourMillis = 60 * 60 * 1000 // 3,600,000 ms
            // if (currentTime - oldTime >= oneHourMillis) {
            //if (basketList.isEmpty())  {
                oldTime = currentTime
                // reportBasket
                val xml = getXmlReport()
                postXml(xml)
            //}
        //}
    }
    private fun getXmlReport() :String {
        val date = Date(oldTime)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault())
        val dateTime = formatter.format(date)

       val receiptList = receiptDao?.getFromDate(dateTime)
        val receiptCount: Int = receiptList?.size ?: 0
        var totalAmount = 0f
        var canceledReceiptsCount = 0
        var canceledReceiptsAmount = 0f
        var vatRate0Amount = 0f
        var vatRate1Amount = 0f
        var vatRate10Amount = 0f
        var vatRate20Amount = 0f
        var cashPaymentAmount = 0f
        var creditPaymentAmount = 0f
        var couponPaymentAmount = 0f
        if (receiptList != null) {
            for (receipt in receiptList) {
                var currentTotalAmount = 0f
                receipt.paymentType?.let {
                    val isNotCancel = if (PAYMENT_METHOD.entries[it] == PAYMENT_METHOD.CANCEL) 0f else 1f
                    var currentVatRateAmount = receipt.amountVat0?.toFloatOrNull() ?: 0f
                    vatRate0Amount += currentVatRateAmount * isNotCancel
                    currentTotalAmount += currentVatRateAmount * isNotCancel
                    currentVatRateAmount = receipt.amountVat1?.toFloatOrNull() ?: 0f
                    vatRate1Amount += currentVatRateAmount * isNotCancel
                    currentTotalAmount += currentVatRateAmount * isNotCancel
                    currentVatRateAmount = receipt.amountVat10?.toFloatOrNull() ?: 0f
                    vatRate10Amount += currentVatRateAmount * isNotCancel
                    currentTotalAmount += currentVatRateAmount * isNotCancel
                    currentVatRateAmount = receipt.amountVat20?.toFloatOrNull() ?: 0f
                    vatRate20Amount += currentVatRateAmount * isNotCancel
                    currentTotalAmount += currentVatRateAmount * isNotCancel
                    when (PAYMENT_METHOD.entries[it]) {
                        PAYMENT_METHOD.CANCEL -> {
                            canceledReceiptsCount++
                            canceledReceiptsAmount += currentTotalAmount
                        }
                        PAYMENT_METHOD.CASH -> {
                            cashPaymentAmount += currentTotalAmount
                        }
                        PAYMENT_METHOD.CREDIT -> {
                            creditPaymentAmount += currentTotalAmount
                        }
                        PAYMENT_METHOD.COUPON -> {
                            couponPaymentAmount += currentTotalAmount
                        }
                    }
                    totalAmount += currentTotalAmount
                }
            }
        }
        // Convert the values to an xml string
        val xml = buildString {
            append("<hourlyReport>")
            append("<reportHour>$dateTime</reportHour>")
            append("<receiptCount>$receiptCount</receiptCount>")
            append("<totalAmount>$totalAmount</totalAmount>")
            append("<canceledInfo>")
            append("<count>$canceledReceiptsCount</count>")
            append("<amount>$canceledReceiptsAmount</amount>")
            append("</canceledInfo>")
            append("<salesVatDistribution>")
            append("<sales>")
            append("<vatRate>0</vatRate>")
            append("<amount>$vatRate0Amount</amount>")
            append("</sales>")
            append("<sales>")
            append("<vatRate>1</vatRate>")
            append("<amount>$vatRate1Amount</amount>")
            append("</sales>")
            append("<sales>")
            append("<vatRate>10</vatRate>")
            append("<amount>$vatRate10Amount</amount>")
            append("</sales>")
            append("<sales>")
            append("<vatRate>20</vatRate>")
            append("<amount>$vatRate20Amount</amount>")
            append("</sales>")
            append("</salesVatDistribution>")
            append("<paymentDistribution>")
            append("<payment>")
            append("<vatRate>Cash</vatRate>")
            append("<amount>$cashPaymentAmount</amount>")
            append("</payment>")
            append("<payment>")
            append("<vatRate>Credit</vatRate>")
            append("<amount>$creditPaymentAmount</amount>")
            append("</payment>")
            append("<payment>")
            append("<vatRate>Coupon</vatRate>")
            append("<amount>$couponPaymentAmount</amount>")
            append("</payment>")
            append("</paymentDistribution>")
            append("</hourlyReport>")
        }
       return xml
    }
    private fun postXml(xmlBody: String) {
        Thread {
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "application/xml")
                connection.setRequestProperty("Accept", "application/xml")

                connection.outputStream.use { it.write(xmlBody.toByteArray()) }
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                println("Server response: $response")
                connection.disconnect()
        }.start()
    }
}