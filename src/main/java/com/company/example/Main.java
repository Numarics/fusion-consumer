package com.company.example;

import com.company.example.document.ContentType;
import com.company.example.document.FileInfo;
import com.company.example.document.PDFToBase64JSON;
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
import com.numarics.engine.fusion.tag.TagApi;
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

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  public CommandLineRunner run() {
    return args -> {
      ClientCreateResponse clientCreateResponse =
          clientApi.create("Demo Client", "demo@example.ch", null, 2, List.of(2));
      System.out.println(clientCreateResponse);

      FileInfo fileInfo = PDFToBase64JSON.getFileInfo("income.pdf", ContentType.PDF.getValue());
      DocumentUploadResponse documentUploadResponse =
          documentApi.upload(
              clientCreateResponse.getTenantUuid(),
              fileInfo.name(),
              fileInfo.size(),
              fileInfo.contentType(),
              fileInfo.content(),
              List.of(1),
              false);
      System.out.println(documentUploadResponse);

      DocumentDetailsResponse documentDetailsResponse =
          documentApi.getById(
              clientCreateResponse.getTenantUuid(),
              (int) documentUploadResponse.getId(),
              null,
              null);
      System.out.println(documentDetailsResponse);

      DocumentDownloadResponse documentDownloadResponse =
          documentApi.download(
              clientCreateResponse.getTenantUuid(), (int) documentUploadResponse.getId());
      System.out.println(documentDownloadResponse);

      DocumentUpdateResponse documentUpdateResponse =
          documentApi.update(
              clientCreateResponse.getTenantUuid(),
              (int) documentUploadResponse.getId(),
              "document.pdf",
              (short) 2,
              (short) 4,
              List.of(2),
              null,
              null,
              null,
              null,
              null,
              null,
              null,
              null);
      System.out.println(documentUpdateResponse);

      DocumentSearchResponse searchResponse =
          documentApi.search(
              clientCreateResponse.getTenantUuid(),
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

      DocumentPreviewResponse documentPreviewResponse =
          documentApi.preview(
              clientCreateResponse.getTenantUuid(), (int) documentUploadResponse.getId());
      System.out.println(documentPreviewResponse);

      DocumentCountByStatusResponse documentCountByStatusResponse =
          documentApi.getDocumentCountByStatus(clientCreateResponse.getTenantUuid());
      System.out.println(documentCountByStatusResponse);

      TagDetailsResponse tagDetailsResponse =
          tagApi.getById(clientCreateResponse.getTenantUuid(), 1);
      System.out.println(tagDetailsResponse);

      TagGetAllResponse tagGetAllResponse = tagApi.getAll(clientCreateResponse.getTenantUuid());
      System.out.println(tagGetAllResponse);

      DocumentBulkActionResponse deleteDocumentsPermanentlyResponse =
          documentApi.deleteDocumentsPermanently(
              clientCreateResponse.getTenantUuid(), List.of(1, 2));
      System.out.println(deleteDocumentsPermanentlyResponse);

      DocumentBulkActionResponse deleteDocumentsSoftlyResponse =
          documentApi.deleteDocumentsSoftly(clientCreateResponse.getTenantUuid(), List.of(3));
      System.out.println(deleteDocumentsSoftlyResponse);

      DocumentBulkActionResponse archiveDocumentsResponse =
          documentApi.archive(clientCreateResponse.getTenantUuid(), List.of(3));
      System.out.println(archiveDocumentsResponse);

      DocumentBulkActionResponse restoreDocumentsResponse =
          documentApi.restore(clientCreateResponse.getTenantUuid(), List.of(3));
      System.out.println(restoreDocumentsResponse);
    };
  }
}
