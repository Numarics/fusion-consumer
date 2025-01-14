package com.company.example;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import com.numarics.engine.fusion.document.DocumentApi;
import com.numarics.engine.fusion.document.DocumentDetailsResponse;
import com.numarics.engine.fusion.document.DocumentDownloadResponse;
import com.numarics.engine.fusion.document.DocumentUpdateResponse;
import com.numarics.engine.fusion.document.DocumentUploadResponse;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {
  private final ClientApi clientApi;
  private final DocumentApi documentApi;

  @Autowired
  public ExampleService(ClientApi clientApi, DocumentApi documentApi) {
    this.clientApi = clientApi;
    this.documentApi = documentApi;
  }

  public String createClient() {
    String clientReference = "";
    String email = "example@email.ch";
    String name = "ref no";
    int type = 2; // 1 is enterprise, 2 is private person
    List<Integer> modules = List.of(2); // 2 for Docubox
    ClientCreateResponse clientCreateResponse =
        clientApi.create(name, email, clientReference, type, modules);
    System.out.println(clientCreateResponse);
    return clientCreateResponse.getTenantUuid();
  }

  public DocumentUploadResponse upload() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from response for onboarding partner client.
    String documentName = "example_doc.pdf";
    Long documentSize = 12345L;
    String contentType = "application/pdf";
    String documentContent = "document_content"; // Document content converted to Base64
    DocumentUploadResponse documentUploadResponse =
        documentApi.upload(tenantUuid, documentName, documentSize, contentType, documentContent);
    System.out.println(documentUploadResponse);
    return documentUploadResponse;
  }

  public DocumentDetailsResponse getById() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from response for onboarding partner client.
    Integer documentId = 1;
    DocumentDetailsResponse response = documentApi.getById(tenantUuid, documentId);
    System.out.println(response);
    return response;
  }

  public DocumentDownloadResponse download() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from response for onboarding partner client.
    Integer documentId = 1;
    DocumentDownloadResponse response = documentApi.download(tenantUuid, documentId);
    System.out.println(response);
    return response;
  }

  public DocumentUpdateResponse update() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from response for onboarding partner client.
    Integer documentId = 1; // Use one from response for upload document.
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
    Short documentAccessType = 1;
    List<String> emails = null;
    Set<Short> roles = null;
    DocumentUpdateResponse response =
        documentApi.update(
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
            currency,
            documentAccessType,
            emails,
            roles);
    System.out.println(response);
    return response;
  }
}
