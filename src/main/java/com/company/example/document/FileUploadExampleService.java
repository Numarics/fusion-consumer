package com.company.example.document;

import com.numarics.engine.fusion.file.FileMetadataRequest;
import com.numarics.engine.fusion.file.FileUploadApi;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileUploadExampleService {
  private static final Logger log = LoggerFactory.getLogger(FileUploadExampleService.class);
  private final FileUploadApi fileUploadApi;

  public FileUploadExampleService(FileUploadApi fileUploadApi) {
    this.fileUploadApi = fileUploadApi;
  }

  private void chunkUploadDocument() {
    String tenantUuid =
        "tenant-uuid-example"; // Use one from a response for an onboarding partner client.
    String documentName = "example_doc.pdf";
    Long documentSize = 12345L;
    String contentType = "application/pdf";
    Boolean isScanned = false;

    try {
      // Build metadata
      var metadata =
          FileMetadataRequest.newBuilder()
              .setTenantUuid(tenantUuid)
              .setName(documentName)
              .setSize(documentSize)
              .setContentType(contentType)
              .setIsScanned(isScanned)
              .addAllTags(new ArrayList<>())
              .build();

      // Read a file in chunks
      List<byte[]> chunks = new ArrayList<>();

      System.out.println(
          "Starting upload for file: "
              .concat(documentName)
              .concat(" total chunks: ")
              .concat(String.valueOf(chunks.size())));

      // Call the upload method
      var response = fileUploadApi.upload(metadata, chunks.iterator());

      System.out.println("Upload completed. Server response: ".concat(String.valueOf(response)));
    } catch (Exception e) {
      log.error("An error occurred: {}", e.getMessage(), e);
    }
  }
}
