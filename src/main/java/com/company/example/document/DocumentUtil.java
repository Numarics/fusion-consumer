package com.company.example.document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public final class DocumentUtil {

  public static FileInfo getFileInfo(String filePath, String contentType) {
    try {
      File file = new File(filePath);

      byte[] fileContent = Files.readAllBytes(file.toPath());

      String encodedContent = Base64.getEncoder().encodeToString(fileContent);
      String fileName = file.getName();
      long fileSize = file.length();

      return new FileInfo(fileName, fileSize, contentType, encodedContent);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
