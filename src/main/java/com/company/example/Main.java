package com.company.example;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.client.ClientCreateResponse;
import com.numarics.engine.fusion.document.DocumentApi;
import com.numarics.engine.fusion.document.DocumentDetailsResponse;
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
          clientApi.create("mihatest12", "mihatest21@t.ch", "test-client-ref-13434", 2, List.of(2));
      System.out.println(response);

      FileInfo fileInfo = PDFToBase64JSON.getFileInfo("income.pdf", ContentType.PDF.getValue());
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
    };
  }
}
