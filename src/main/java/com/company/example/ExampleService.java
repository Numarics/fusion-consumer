package com.company.example;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import com.numarics.engine.fusion.document.DocumentApi;
import com.numarics.engine.fusion.document.DocumentUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String clientReference = "some client reference";
        String email = "test_email@email.ch";
        String name = "test";
        int type = 2; // 1 is enterprise, 2 is private person
        List<Integer> modules = List.of(2); // 2 for Docubox
        ClientCreateResponse clientCreateResponse = clientApi.create("test", "t@t.ch", "test-client-ref-2", type, modules);
        System.out.println(clientCreateResponse);
        return clientCreateResponse.getTenantUuid();
    }

    public DocumentUploadResponse upload() {
        String tenantUuid = "tenant-uuid-example"; // Use one from response for onboarding partner client.
        String documentName = "example_doc.pdf";
        Long documentSize = 12345L;
        String contentType = "application/pdf";
        String documentContent = "document_content"; // Document content converted to Base64
        DocumentUploadResponse documentUploadResponse = documentApi.upload(tenantUuid,
                documentName, documentSize, contentType, documentContent);
        System.out.println(documentUploadResponse);
        return documentUploadResponse;
    }
}
