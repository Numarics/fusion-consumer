package com.company.example;

import com.numarics.engine.fusion.client.ClientApi;
import com.numarics.engine.fusion.document.DocumentApi;
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
      System.out.println(
          clientApi.create("test21", "t21@t.ch", "test-client-ref-121", 2, List.of(2)));
      FileInfo fileInfo = PDFToBase64JSON.getFileInfo("document_winner.pdf", ContentType.PDF.getValue());
      System.out.println(
          documentApi.upload(
              "307e450e-b638-4885-8acf-889c7eae54d8",
              fileInfo.name(),
              fileInfo.size(),
              fileInfo.contentType(),
              fileInfo.content()));
    };
  }
}
