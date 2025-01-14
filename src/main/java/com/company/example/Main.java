package com.company.example;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import com.numarics.engine.fusion.document.DocumentApi;
import com.numarics.engine.fusion.document.DocumentDetailsResponse;
import com.numarics.engine.fusion.document.DocumentDownloadResponse;
import com.numarics.engine.fusion.document.DocumentUpdateResponse;
import com.numarics.engine.fusion.document.DocumentUploadResponse;
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

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  public CommandLineRunner run() {
    return args -> {
            ClientCreateResponse response =
                clientApi.create("noref3", "noref3@t.ch", null, 2, List.of(2));
            System.out.println(response);

            FileInfo fileInfo = PDFToBase64JSON.getFileInfo("income.pdf",
       ContentType.PDF.getValue());
            DocumentUploadResponse uploadResponse =
                documentApi.upload(
                    response.getTenantUuid(),
                    fileInfo.name(),
                    fileInfo.size(),
                    fileInfo.contentType(),
                    fileInfo.content());
            System.out.println(uploadResponse);

            DocumentDetailsResponse documentDetailsResponse =
                documentApi.getById(response.getTenantUuid(), (int) uploadResponse.getId());
            System.out.println(documentDetailsResponse);

            DocumentDownloadResponse documentDownloadResponse =
                documentApi.download(response.getTenantUuid(), (int) uploadResponse.getId());
            System.out.println(documentDownloadResponse);

      DocumentUpdateResponse documentUpdateResponse =
          documentApi.update(
              "307e450e-b638-4885-8acf-889c7eae54d8",
              21,
              null,
              (short) 2,
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
      System.out.println(documentUpdateResponse);
    };
  }
}
