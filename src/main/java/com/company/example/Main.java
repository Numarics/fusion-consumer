package com.company.example;

import com.company.example.document.FileInfo;
import com.company.example.document.FileUtils;
import com.company.example.document.MimeType;
import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
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
import com.numarics.engine.fusion.file.FileMetadataRequest;
import com.numarics.engine.fusion.file.FileUploadApi;
import com.numarics.engine.fusion.tag.TagApi;
import com.numarics.engine.fusion.tag.TagDeleteResponse;
import com.numarics.engine.fusion.tag.TagDetailsResponse;
import com.numarics.engine.fusion.tag.TagGetAllResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.numarics.engine.fusion", "com.company.example"})
public class Main {
  @Autowired private ClientApi clientApi;

  @Autowired private DocumentApi documentApi;

  @Autowired private TagApi tagApi;

  @Autowired private FileUploadApi fileUploadApi;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  public CommandLineRunner run() {
    return args -> {
      // TODO: Uncomment the relevant section of the code that you would like to test.

      // String tenantUuid = createClient();
      // chunkUploadDocument(tenantUuid, "src/main/resources/files/income.pdf", MimeType.PDF);
      // Integer documentId = uploadDocument(tenantUuid, "src/main/resources/files/income.pdf",
      // MimeType.PDF);

      // getDocumentById(tenantUuid, documentId);
      // downloadDocument(tenantUuid, 6);
      // updateDocument(tenantUuid, documentId);
      // searchDocuments(tenantUuid);
      // previewDocument(tenantUuid, documentId);
      // getDocumentCountByStatus(tenantUuid);
      // updatePaymentStatus(tenantUuid, documentId, (short) 1);
      //
      // deleteDocumentsPermanently(tenantUuid, List.of(documentId));
      // deleteDocumentsSoftly(tenantUuid, List.of(documentId));
      // archiveDocuments(tenantUuid, List.of(documentId));
      // restoreDocuments(tenantUuid, List.of(documentId));
      //
      // Integer tagId = 1;
      // getTagById(tenantUuid, 1);
      // getAllTags(tenantUuid);
      // Integer rootTagId = createRootTag(tenantUuid);
      // updateRootTag(tenantUuid, rootTagId);
      //
      // Integer childTagId = createChildTag(tenantUuid, tagId);
      // updateChildTag(tenantUuid, childTagId);
      //
      // deleteTag(tenantUuid, tagId);
    };
  }

  private String createClient() {
    ClientCreateResponse clientCreateResponse =
        clientApi.create("Demo Client", "demo-client@example.ch", "demo-client", 2, List.of(2));
    System.out.println(clientCreateResponse);
    return clientCreateResponse.getTenantUuid();
  }

  private int uploadDocument(String tenantUuid, String filePath, MimeType mimeType) {
    FileInfo fileInfo = FileUtils.getFileInfo(filePath, mimeType.getValue());
    DocumentUploadResponse documentUploadResponse =
        documentApi.upload(
            tenantUuid,
            fileInfo.name(),
            fileInfo.size(),
            fileInfo.contentType(),
            fileInfo.content(),
            null,
            false);
    System.out.println(documentUploadResponse);
    return (int) documentUploadResponse.getId();
  }

  private void chunkUploadDocument(String tenantUuid, String filePath, MimeType mimeType) {
    FileInfo fileInfo = FileUtils.getFileInfo(filePath, mimeType.getValue());
    try {
      // Build metadata
      FileMetadataRequest metadata =
          FileMetadataRequest.newBuilder()
              .setTenantUuid(tenantUuid)
              .setName(fileInfo.name())
              .setSize(fileInfo.size())
              .setContentType(fileInfo.contentType())
              .build();

      System.out.println(
          "Starting upload for file: "
              .concat(filePath)
              .concat(" total chunks: ")
              .concat(String.valueOf(fileInfo.chunks().size())));

      // Call the upload method
      var response = fileUploadApi.upload(metadata, fileInfo.chunks().iterator());

      System.out.println("Upload completed. Server response: ".concat(String.valueOf(response)));
    } catch (Exception e) {
      System.err.println(e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  private void getDocumentById(String tenantUuid, Integer documentId) {
    DocumentDetailsResponse documentDetailsResponse =
        documentApi.getById(tenantUuid, documentId, null, null);
    System.out.println(documentDetailsResponse);
  }

  private void downloadDocument(String tenantUuid, Integer documentId) {
    DocumentDownloadResponse documentDownloadResponse =
        documentApi.download(tenantUuid, documentId);
    System.out.println(documentDownloadResponse);
  }

  private void updateDocument(String tenantUuid, Integer documentId) {
    DocumentUpdateResponse documentUpdateResponse =
        documentApi.update(
            tenantUuid,
            documentId,
            null,
            null,
            null,
            List.of(40, 44, 43, 41),
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null);
    System.out.println(documentUpdateResponse);
  }

  private void searchDocuments(String tenantUuid) {
    DocumentSearchResponse searchResponse =
        documentApi.search(
            tenantUuid,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null);
    System.out.println(searchResponse);
  }

  private void previewDocument(String tenantUuid, Integer documentId) {
    DocumentPreviewResponse documentPreviewResponse = documentApi.preview(tenantUuid, documentId);
    System.out.println(documentPreviewResponse);
  }

  private void getDocumentCountByStatus(String tenantUuid) {
    DocumentCountByStatusResponse documentCountByStatusResponse =
        documentApi.getDocumentCountByStatus(tenantUuid);
    System.out.println(documentCountByStatusResponse);
  }

  private void deleteDocumentsPermanently(String tenantUuid, List<Integer> documentIds) {
    DocumentBulkActionResponse deleteDocumentsPermanentlyResponse =
        documentApi.deleteDocumentsPermanently(tenantUuid, documentIds);
    System.out.println(deleteDocumentsPermanentlyResponse);
  }

  private void deleteDocumentsSoftly(String tenantUuid, List<Integer> documentIds) {
    DocumentBulkActionResponse deleteDocumentsSoftlyResponse =
        documentApi.deleteDocumentsSoftly(tenantUuid, documentIds);
    System.out.println(deleteDocumentsSoftlyResponse);
  }

  private void archiveDocuments(String tenantUuid, List<Integer> documentIds) {
    DocumentBulkActionResponse archiveDocumentsResponse =
        documentApi.archive(tenantUuid, documentIds);
    System.out.println(archiveDocumentsResponse);
  }

  private void restoreDocuments(String tenantUuid, List<Integer> documentIds) {
    DocumentBulkActionResponse restoreDocumentsResponse =
        documentApi.restore(tenantUuid, documentIds);
    System.out.println(restoreDocumentsResponse);
  }

  private void updatePaymentStatus(String tenantUuid, Integer documentId, Short paymentStatus) {
    UpdatePaymentStatusResponse updatePaymentStatusResponse =
        documentApi.updatePaymentStatus(tenantUuid, documentId, paymentStatus);
    System.out.println(updatePaymentStatusResponse);
  }

  private void getTagById(String tenantUuid, Integer tagId) {
    TagDetailsResponse tagDetailsResponse = tagApi.getById(tenantUuid, tagId);
    System.out.println(tagDetailsResponse);
  }

  private void getAllTags(String tenantUuid) {
    TagGetAllResponse tagGetAllResponse = tagApi.getAll(tenantUuid);
    System.out.println(tagGetAllResponse);
  }

  private Integer createRootTag(String tenantUuid) {
    TagDetailsResponse rootTagCreateResponse =
        tagApi.createRootTag(tenantUuid, "Demo Tag", "#FFE4E5E1");
    System.out.println(rootTagCreateResponse);
    return (int) rootTagCreateResponse.getId();
  }

  private void updateRootTag(String tenantUuid, Integer tagId) {
    TagDetailsResponse rootTagUpdateResponse =
        tagApi.updateRootTag(tenantUuid, tagId, "Demo Tag Update", "#FFE4E5E2");
    System.out.println(rootTagUpdateResponse);
  }

  private Integer createChildTag(String tenantUuid, Integer tagId) {
    TagDetailsResponse childTagCreateResponse =
        tagApi.createChildTag(tenantUuid, "Demo Tag", tagId);
    System.out.println(childTagCreateResponse);
    return (int) childTagCreateResponse.getId();
  }

  private void updateChildTag(String tenantUuid, Integer tagId) {
    TagDetailsResponse childTagUpdateResponse =
        tagApi.updateChildTag(tenantUuid, tagId, "Demo Tag Update", tagId);
    System.out.println(childTagUpdateResponse);
  }

  private void deleteTag(String tenantUuid, Integer tagId) {
    TagDeleteResponse tagDeleteResponse = tagApi.delete(tenantUuid, tagId);
    System.out.println(tagDeleteResponse);
  }
}
