package com.company.example;

import com.company.example.document.ContentType;
import com.company.example.document.FileInfo;
import com.company.example.document.PDFToBase64JSON;
import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import com.numarics.engine.fusion.document.DocumentApi;
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
          clientApi.create("noref4", "noref4@t.ch", null, 2, List.of(2));
      System.out.println(clientCreateResponse);

      FileInfo fileInfo = PDFToBase64JSON.getFileInfo("income.pdf", ContentType.PDF.getValue());
      DocumentUploadResponse documentUploadResponse =
          documentApi.upload(
              "307e450e-b638-4885-8acf-889c7eae54d8",
              fileInfo.name(),
              fileInfo.size(),
              fileInfo.contentType(),
              fileInfo.content(),
              null,
              null,
              null,
              null,
              null);
      System.out.println(documentUploadResponse);

      DocumentDetailsResponse documentDetailsResponse =
          documentApi.getById("307e450e-b638-4885-8acf-889c7eae54d8", 21);
      System.out.println(documentDetailsResponse);

      DocumentDownloadResponse documentDownloadResponse =
          documentApi.download("307e450e-b638-4885-8acf-889c7eae54d8", 22);
      System.out.println(documentDownloadResponse);

      DocumentUpdateResponse documentUpdateResponse =
          documentApi.update(
              "307e450e-b638-4885-8acf-889c7eae54d8",
              21,
              null,
              (short) 2,
              null,
              List.of(2),
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
      System.out.println(documentUpdateResponse);

      DocumentSearchResponse searchResponse =
          documentApi.search(
              "307e450e-b638-4885-8acf-889c7eae54d8",
              null,
              null,
              null,
              null,
              null,
              null,
              "2024-08-30",
              "2024-09-01",
              null,
              null,
              null,
              null,
              null,
              null,
              null);
      System.out.println(searchResponse);

      DocumentPreviewResponse documentPreviewResponse =
          documentApi.preview("307e450e-b638-4885-8acf-889c7eae54d8", 20);
      System.out.println(documentPreviewResponse);

      TagDetailsResponse tagDetailsResponse =
          tagApi.getById("307e450e-b638-4885-8acf-889c7eae54d8", 2);
      System.out.println(tagDetailsResponse);

      TagGetAllResponse tagGetAllResponse = tagApi.getAll("307e450e-b638-4885-8acf-889c7eae54d8");
      System.out.println(tagGetAllResponse);
    };
  }
}
