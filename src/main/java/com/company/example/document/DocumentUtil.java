package com.company.example.document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public final class DocumentUtil {

  public static FileInfo getFileInfo(String filePath, String contentType) {
    return getFileInfo(filePath, contentType, false);
  }

  public static FileInfo getFileInfoEncoded(String filePath, String contentType) {
    return getFileInfo(filePath, contentType, true);
  }

  public static FileInfo getFileInfo(String filePath, String contentType, boolean encodeBase64) {
    try {
      File file = new File(filePath);

      byte[] fileContent = Files.readAllBytes(file.toPath());
      String fileData =
          encodeBase64 ? Base64.getEncoder().encodeToString(fileContent) : new String(fileContent);

      String fileName = file.getName();
      long fileSize = file.length();

      return new FileInfo(fileName, fileSize, contentType, fileData);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
