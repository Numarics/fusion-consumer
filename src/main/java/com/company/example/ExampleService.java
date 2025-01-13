package com.company.example;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import com.numarics.engine.fusion.document.DocumentApi;
import com.numarics.engine.fusion.document.DocumentDetailsResponse;
import com.numarics.engine.fusion.document.DocumentDownloadResponse;
import com.numarics.engine.fusion.document.DocumentUpdateRequest;
import com.numarics.engine.fusion.document.DocumentUpdateResponse;
import com.numarics.engine.fusion.document.DocumentUploadResponse;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
}
