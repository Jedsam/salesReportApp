package com.backend.controller;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import com.backend.proto.sync.SyncRequest;
import com.backend.proto.sync.SyncResponse;
import com.backend.proto.sync.Transaction;
import com.backend.proto.sync.TransactionItem;
import com.backend.proto.sync.SyncServiceGrpc.SyncServiceImplBase;
import com.backend.service.JwtService;
import com.google.protobuf.ByteString;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SyncGrpcService extends SyncServiceImplBase {

  private final JwtService jwtService;

  public SyncGrpcService(
      JwtService jwtService) {
    this.jwtService = jwtService;
  }

  /**
   * Generates an XML report string based on the data in the SyncRequest.
   * This logic is migrated from the old Kotlin function and adapted
   * to the new Protobuf data structure.
   *
   * @param syncRequest The proto request containing all sync data.
   * @return An XML string.
   */
  private String getXmlReport(SyncRequest syncRequest) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    String dateTime = LocalDateTime.now().format(formatter);

    int receiptCount = 0;
    double totalAmount = 0.0;
    int canceledReceiptsCount = 0;
    double canceledReceiptsAmount = 0.0;
    double vatRate0Amount = 0.0;
    double vatRate1Amount = 0.0;
    double vatRate10Amount = 0.0;
    double vatRate20Amount = 0.0;
    double cashPaymentAmount = 0.0;
    double creditPaymentAmount = 0.0;
    double couponPaymentAmount = 0.0;

    Map<ByteString, String> transactionPaymentType = new HashMap<>();

    receiptCount = syncRequest.getTransactionsCount();

    for (Transaction transaction : syncRequest.getTransactionsList()) {
      ByteString txId = transaction.getTransactionId();
      String paymentType = transaction.getPaymentType();
      double transactionTotal = transaction.getTotal();

      transactionPaymentType.put(txId, paymentType);

      switch (paymentType) {
        case "cancel":
          canceledReceiptsCount++;
          canceledReceiptsAmount += transactionTotal;
          break;
        case "cash":
          cashPaymentAmount += transactionTotal;
          totalAmount += transactionTotal;
          break;
        case "credit":
          creditPaymentAmount += transactionTotal;
          totalAmount += transactionTotal;
          break;
        case "coupon":
          couponPaymentAmount += transactionTotal;
          totalAmount += transactionTotal;
          break;
        default:

          totalAmount += transactionTotal;
          break;
      }
    }

    for (TransactionItem item : syncRequest.getTransactionItemsList()) {
      String paymentType = transactionPaymentType.get(item.getTransactionId());

      if (paymentType != null && !paymentType.equals("cancel")) {
        double itemTotal = item.getTotal();
        double vatRate = item.getVatRate();

        double vatAmount = itemTotal - (itemTotal / (1.0 + vatRate));

        if (Math.abs(vatRate - 0.0) < 0.0001) {
          vatRate0Amount += vatAmount;
        } else if (Math.abs(vatRate - 1.00) < 0.0001) {
          vatRate1Amount += vatAmount;
        } else if (Math.abs(vatRate - 10.0) < 0.0001) {
          vatRate10Amount += vatAmount;
        } else if (Math.abs(vatRate - 20.0) < 0.0001) {
          vatRate20Amount += vatAmount;
        }
      }
    }
    StringBuilder xml = new StringBuilder();
    xml.append("<hourlyReport>");
    xml.append("<reportHour>").append(dateTime).append("</reportHour>");
    xml.append("<receiptCount>").append(receiptCount).append("</receiptCount>");
    xml.append("<totalAmount>").append(totalAmount).append("</totalAmount>");
    xml.append("<canceledInfo>");
    xml.append("<count>").append(canceledReceiptsCount).append("</count>");
    xml.append("<amount>").append(canceledReceiptsAmount).append("</amount>");
    xml.append("</canceledInfo>");
    xml.append("<salesVatDistribution>");
    xml.append("<sales>");
    xml.append("<vatRate>0</vatRate>");
    xml.append("<amount>").append(vatRate0Amount).append("</amount>");
    xml.append("</sales>");
    xml.append("<sales>");
    xml.append("<vatRate>1</vatRate>");
    xml.append("<amount>").append(vatRate1Amount).append("</amount>");
    xml.append("</sales>");
    xml.append("<sales>");
    xml.append("<vatRate>10</vatRate>");
    xml.append("<amount>").append(vatRate10Amount).append("</amount>");
    xml.append("</sales>");
    xml.append("<sales>");
    xml.append("<vatRate>20</vatRate>");
    xml.append("<amount>").append(vatRate20Amount).append("</amount>");
    xml.append("</sales>");
    xml.append("</salesVatDistribution>");
    xml.append("<paymentDistribution>");
    xml.append("<payment>");
    xml.append("<vatRate>Cash</vatRate>");
    xml.append("<amount>").append(cashPaymentAmount).append("</amount>");
    xml.append("</payment>");
    xml.append("<payment>");
    xml.append("<vatRate>Credit</vatRate>");
    xml.append("<amount>").append(creditPaymentAmount).append("</amount>");
    xml.append("</payment>");
    xml.append("<payment>");
    xml.append("<vatRate>Coupon</vatRate>");
    xml.append("<amount>").append(couponPaymentAmount).append("</amount>");
    xml.append("</payment>");
    xml.append("</paymentDistribution>");
    xml.append("</hourlyReport>");

    return xml.toString();
  }

  private void saveXmlToFile(String xmlContent, String filePath) {
    try {
      // Create directories if they don't exist
      Files.createDirectories(Paths.get(filePath).getParent());

      // Write the string to the file, creating it if it doesn't exist
      // or overwriting it if it does.
      Files.writeString(
          Paths.get(filePath),
          xmlContent,
          StandardCharsets.UTF_8,
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING);

      log.info("Successfully saved XML report to: " + filePath);

    } catch (IOException e) {
      log.error("Failed to save XML report to file: " + filePath, e);
    }
  }

  @Override
  @PreAuthorize("hasRole('MERCHANT')")
  public void syncDevice(SyncRequest syncRequest,
      StreamObserver<SyncResponse> responseObserver) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    String xmlReport = getXmlReport(syncRequest);

    // (You'll want to make this dynamic, maybe using the shop_id or device_id)
    String fileName = "sync-report-" + System.currentTimeMillis() + ".xml";
    String filePath = "reports/" + fileName; // Saves to a 'reports' directory

    // 3. Save the XML to the file
    saveXmlToFile(xmlReport, filePath);

    responseObserver.onNext(
        SyncResponse.newBuilder()
            .setResult(1)
            .build());
    responseObserver.onCompleted();
  }

  private static final Logger log = LoggerFactory.getLogger(SyncGrpcService.class);

}
