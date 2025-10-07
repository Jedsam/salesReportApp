package com.example.frontendinternship

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

enum class PAYMENT_METHOD {
    CANCEL, CASH, CREDIT, COUPON
}
@Entity(tableName = "receipt")
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    val receiptNumber: Int? = null,
    @ColumnInfo(name = "receiptDateTime") val receiptDateTime: String?,
    @ColumnInfo(name = "amountVat0") val amountVat0: String?,
    @ColumnInfo(name = "amountVat1") val amountVat1: String?,
    @ColumnInfo(name = "amountVat10") val amountVat10: String?,
    @ColumnInfo(name = "amountVat20") val amountVat20: String?,
    @ColumnInfo(name = "paymentType") val paymentType: Int?,
)

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipt")
    fun getAll(): List<Receipt>
@Insert
fun insert(receipt: Receipt)

    @Insert
    fun insert(basketList: List<ProductWithCount>, paymentType: PAYMENT_METHOD) {
       var amountVat0: Int = 0
        var amountVat1: Int = 0
        var amountVat10: Int = 0
        var amountVat20: Int = 0

        for (productWithCount in basketList) {
           when (productWithCount.product.vatRate) {
               0 -> amountVat0 += productWithCount.count
               1 -> amountVat1 += productWithCount.count
               10 -> amountVat10 += productWithCount.count
               20 -> amountVat20 += productWithCount.count
           }
        }
        val currentMillis = System.currentTimeMillis()

        val date = Date(currentMillis)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault())
        val dateTime = formatter.format(date)
        insert(Receipt(receiptDateTime = dateTime,
            amountVat0 = amountVat0.toString(),
            amountVat1 = amountVat1.toString(),
            amountVat10 = amountVat10.toString(),
            amountVat20 = amountVat20.toString(),
            paymentType = paymentType.ordinal))
    }

    @Insert
    fun insertAll(vararg receipts: Receipt)

    @Delete
    fun delete(receipt: Receipt)
}

