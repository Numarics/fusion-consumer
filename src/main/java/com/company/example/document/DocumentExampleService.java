package com.company.example.document;

import com.numarics.engine.fusion.document.DocumentApi;
import com.numarics.engine.fusion.document.DocumentBulkActionResponse;
import com.numarics.engine.fusion.document.DocumentCountByStatusResponse;
import com.numarics.engine.fusion.document.DocumentDetailsResponse;
import com.numarics.engine.fusion.document.DocumentDownloadResponse;
import com.numarics.engine.fusion.document.DocumentPreviewResponse;
import com.numarics.engine.fusion.document.DocumentSearchResponse;
import com.numarics.engine.fusion.document.DocumentUpdateResponse;
import com.numarics.engine.fusion.document.DocumentUploadResponse;
import com.numarics.engine.fusion.document.UpdatePaymentStatusResponse;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentExampleService {
  private final DocumentApi documentApi;

  @Autowired
  public DocumentExampleService(DocumentApi documentApi) {
    this.documentApi = documentApi;
  }

  public DocumentUploadResponse upload() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from a response for an onboarding partner client.
    String documentName = "example_doc.pdf";
    Long documentSize = 12345L;
    String contentType = "application/pdf";
    String documentContent = "document_content"; // Document content converted to Base64
    Boolean isScanned = false;
    return documentApi.upload(
        tenantUuid, documentName, documentSize, contentType, documentContent, null, isScanned);
  }

  public DocumentDetailsResponse getById() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from a response for an onboarding partner client.
    Integer documentId = 1; // Use one from response for an upload document.
    Boolean includePreview = false;
    Boolean includeContent = false;
    return documentApi.getById(tenantUuid, documentId, includePreview, includeContent);
  }

  public DocumentDownloadResponse download() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer documentId = 1; // Use one from response for an upload document.
    return documentApi.download(tenantUuid, documentId);
  }

  public DocumentUpdateResponse update() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer documentId = 1; // Use one from response for an upload document.
    String name = "example_doc_1.pdf";
    Short type = 1;
    Short status = 1;
    List<Integer> tags = List.of(1);
    String vendorName = "Vendor Example";
    String vendorAddress = "Vendor Address Example";
    String vendorTaxId = "12345";
    String dueDate = "2024-01-01";
    String documentDate = "2024-01-01";
    String vatRate = "0.1";
    String totalAmount = "100";
    String currency = "CHF";
    return documentApi.update(
        tenantUuid,
        documentId,
        name,
        type,
        status,
        tags,
        vendorName,
        vendorAddress,
        vendorTaxId,
        dueDate,
        documentDate,
        vatRate,
        totalAmount,
        currency);
  }

  public DocumentSearchResponse search() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    String term = "example_doc_1.pdf";
    Set<Integer> tags = Set.of(1);
    Set<Short> statuses = Set.of((short) 1);
    String createdAtFrom = "2024-01-01";
    String createdAtTo = "2024-01-02";
    String contactName = "Contact Example";
    String documentDateFrom = "2024-01-01";
    String documentDateTo = "2024-01-01";
    Short documentType = 1;
    Boolean isDmbDocument = false;
    Boolean isNewDocument = false;
    Boolean includePreview = false;
    Boolean includeContent = false;
    Set<Short> paymentStatuses = Set.of((short) 1);
    String paymentStatusUpdatedAtFrom = "2024-01-01";
    String paymentStatusUpdatedAtTo = "2024-01-01";
    Integer offset = 0;
    Integer limit = 10;
    String sortBy = "created";
    String orderBy = "desc";
    return documentApi.search(
        tenantUuid,
        term,
        tags,
        statuses,
        createdAtFrom,
        createdAtTo,
        contactName,
        documentDateFrom,
        documentDateTo,
        documentType,
        isDmbDocument,
        isNewDocument,
        includePreview,
        includeContent,
        paymentStatuses,
        paymentStatusUpdatedAtFrom,
        paymentStatusUpdatedAtTo,
        offset,
        limit,
        sortBy,
        orderBy);
  }

  public DocumentPreviewResponse preview() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer documentId = 1; // Use one from response for an upload document.
    return documentApi.preview(tenantUuid, documentId);
  }

  public DocumentCountByStatusResponse getDocumentCountByStatus() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    return documentApi.getDocumentCountByStatus(tenantUuid);
  }

  public DocumentBulkActionResponse deleteDocumentsPermanently() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    List<Integer> documentIds = List.of(1); // Use one from response for an upload document.
    return documentApi.deleteDocumentsPermanently(tenantUuid, documentIds);
  }

  public DocumentBulkActionResponse deleteDocumentsSoftly() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    List<Integer> documentIds = List.of(1); // Use one from response for an upload document.
    return documentApi.deleteDocumentsSoftly(tenantUuid, documentIds);
  }

  public DocumentBulkActionResponse archive() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    List<Integer> documentIds = List.of(1); // Use one from response for an upload document.
    return documentApi.archive(tenantUuid, documentIds);
  }

  public DocumentBulkActionResponse restore() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    List<Integer> documentIds = List.of(1); // Use one from response for an upload document.
    return documentApi.restore(tenantUuid, documentIds);
  }

  public UpdatePaymentStatusResponse updatePaymentStatus() {
    String tenantUuid =
        "demo-tenant-uuid"; // Use one from a response for an onboarding partner client.
    Integer documentId = 1; // Use one from response for an upload document.
    Short paymentStatus = 1;
    return documentApi.updatePaymentStatus(tenantUuid, documentId, paymentStatus);
  }
}
